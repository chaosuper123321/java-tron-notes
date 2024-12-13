package org.tron.common.utils;

import static org.tron.common.utils.StringUtil.encode58Check;

import com.google.common.collect.Maps;
import com.google.common.collect.Streams;
import com.google.protobuf.ByteString;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.tron.common.parameter.CommonParameter;
import org.tron.core.ChainBaseManager;
import org.tron.core.capsule.BlockCapsule;
import org.tron.core.config.Parameter.ForkBlockVersionConsts;
import org.tron.core.config.Parameter.ForkBlockVersionEnum;
import org.tron.core.store.DynamicPropertiesStore;

/*
  核心与辅助操作：核心操作包括版本检查（pass方法）、版本升级和降级（update方法），以及状态重置（reset方法）。辅助操作包括初始化和获取实例。

  操作序列：在区块链运行过程中，update方法会根据新产生的区块信息不断调用，以决定是否需要版本升级或降级。reset方法在特定情况下用于重置状态。

  性能方面：此模块需要高效地处理版本检查和更新操作，尤其是在区块链网络中节点数量众多时。优化数组和循环操作对提升性能至关重要。

  可重用性：ForkController设计为可重用的单例模式，可在需要管理分叉版本的不同场景下使用。

  使用：在区块链系统中，此模块被用于管理网络中的版本升级和降级，以确保网络的平稳运行和新特性的顺利引入。
 */

/**
 * 管理和控制区块链网络中的硬分叉版本升级和降级。
 * 它负责检测网络中的节点是否支持新的区块版本，并据此决定是否激活新版本的特性。
 */
@Slf4j(topic = "utils")
public class ForkController {

  //VERSION_UPGRADE 和 VERSION_DOWNGRADE: 分别表示版本升级和降级的标志。
  private static final byte VERSION_DOWNGRADE = (byte) 0;
  private static final byte VERSION_UPGRADE = (byte) 1;
  //check: 用于记录版本检查结果的数组。
  private static final byte[] check;

  static {
    check = new byte[1024];
    Arrays.fill(check, VERSION_UPGRADE);
  }

  //manager: ChainBaseManager的实例，用于访问动态属性存储等。
  @Getter
  private ChainBaseManager manager;

  //初始化ForkController，设置基础管理器并更新最新版本信息。
  public void init(ChainBaseManager manager) {
    this.manager = manager;
    DynamicPropertiesStore store = manager.getDynamicPropertiesStore();
    int latestVersion = store.getLatestVersion();
    if (latestVersion == 0) {
      for (ForkBlockVersionEnum version : ForkBlockVersionEnum.values()) {
        int v = version.getValue();
        if (pass(v) && latestVersion < v) {
          latestVersion = v;
        }
      }
      store.saveLatestVersion(latestVersion);
    }
  }

  //检查是否通过指定的分叉版本。
  public boolean pass(ForkBlockVersionEnum forkBlockVersionEnum) {
    return pass(forkBlockVersionEnum.getValue());
  }

  public synchronized boolean pass(int version) {
    if (manager == null) {
      throw new IllegalStateException("not inited");
    }
    if (version > ForkBlockVersionEnum.VERSION_4_0.getValue()) {
      return passNew(version);
    } else {
      return passOld(version);
    }
  }

  private boolean passOld(int version) {
    if (version == ForkBlockVersionConsts.ENERGY_LIMIT) {
      return checkForEnergyLimit();
    }

    byte[] stats = manager.getDynamicPropertiesStore().statsByVersion(version);
    return check(stats);
  }

  //检查某个区块版本是否满足硬分叉的条件，包括版本的存在性、时间验证以及统计数据的比较。
  private boolean passNew(int version) {
    //调用getForkBlockVersionEnum(version)  来获取与传入的  version  相关的枚举值。
    ForkBlockVersionEnum versionEnum = ForkBlockVersionEnum.getForkBlockVersionEnum(version);
    //检查  versionEnum  是否为  null
    if (versionEnum == null) {
      //如果是，则记录一条错误日志，表明不存在该区块版本，
      logger.error("Not exist block version: {}.", version);
      //并返回  false ，表示检查未通过。
      return false;
    }
    //从  manager  对象的动态属性存储中获取最新区块头的时间戳
    long latestBlockTime = manager.getDynamicPropertiesStore().getLatestBlockHeaderTimestamp();
    //获取维护时间间隔，并将其存储在  maintenanceTimeInterval  变量中。
    long maintenanceTimeInterval = manager.getDynamicPropertiesStore().getMaintenanceTimeInterval();
    //计算硬分叉时间  hardForkTime 。
    //首先从  versionEnum  中获取硬分叉时间，减去 1 后除以维护时间间隔，再加 1，最后乘以维护时间间隔，得到一个向上取整的时间点。
    long hardForkTime = ((versionEnum.getHardForkTime() - 1) / maintenanceTimeInterval + 1)
        * maintenanceTimeInterval;
    //检查最新区块时间是否小于计算出的硬分叉时间。
    if (latestBlockTime < hardForkTime) {
      //如果是，则返回  false ，表示检查未通过。
      return false;
    }
    //调用动态属性存储中的  statsByVersion(version)  方法获取与该版本相关的统计数据，并将其存储在  stats  数组中。
    byte[] stats = manager.getDynamicPropertiesStore().statsByVersion(version);
    //检查  stats  是否为  null  或者长度为 0。
    if (stats == null || stats.length == 0) {
      //如果是，则返回  false ，表示检查未通过。
      return false;
    }
    //初始化一个计数器  count ，用于统计符合条件的元素数量。
    int count = 0;
    //循环遍历  stats  数组，
    for (int i = 0; i < stats.length; i++) {
      //检查每个元素是否与  check  数组中的对应元素相等。
      if (check[i] == stats[i]) {
        //如果相等，则将  count  增加 1。
        ++count;
      }
    }
    //表示  count  是否大于或等于硬分叉率（ hardForkRate ）所计算出的阈值。
    //这个阈值是通过将硬分叉率乘以  stats  数组的长度，除以 100，然后向上取整得到的。
    return count >= Math
        .ceil((double) versionEnum.getHardForkRate() * stats.length / 100);
  }


  // when block.version = 5,
  // it make block use new energy to handle transaction when block number >= 4727890L.
  // version !=5, skip this.
  private boolean checkForEnergyLimit() {
    long blockNum = manager.getDynamicPropertiesStore().getLatestBlockHeaderNumber();
    return blockNum >= CommonParameter.getInstance().getBlockNumForEnergyLimit();
  }

  private boolean check(byte[] stats) {
    if (stats == null || stats.length == 0) {
      return false;
    }

    for (int i = 0; i < stats.length; i++) {
      if (check[i] != stats[i]) {
        return false;
      }
    }

    return true;
  }

  /**
   * 遍历所有的区块版本，检查每个版本是否大于传入的版本并且未通过验证。
   * 如果满足条件，则对该版本的统计数据进行降级操作，并将更新后的数据保存回存储中。
   * @param version
   * @param slot
   */
  private void downgrade(int version, int slot) {
    //遍历  ForkBlockVersionEnum  枚举类的所有值。 versionEnum  变量将依次存储每个枚举值。
    for (ForkBlockVersionEnum versionEnum : ForkBlockVersionEnum.values()) {
      //调用  versionEnum  的  getValue()  方法获取当前枚举值对应的版本号
      int versionValue = versionEnum.getValue();
      //检查  versionValue  是否大于传入的  version
      //调用  pass(versionValue)  方法检查该版本是否通过验证。
      if (versionValue > version && !pass(versionValue)) {
        //从  manager  对象的动态属性存储中获取与当前版本  versionValue  相关的统计数据，并将其存储在  stats  数组中。
        byte[] stats = manager.getDynamicPropertiesStore().statsByVersion(versionValue);
        //检查  stats  是否不为  null ，即确认获取到的统计数据存在。
        if (Objects.nonNull(stats)) {
          //将  stats  数组中索引为  slot  的位置的值设置为  VERSION_DOWNGRADE 。
          //这表示对该版本的降级操作。
          stats[slot] = VERSION_DOWNGRADE;
          //将更新后的  stats  数组重新保存回动态属性存储中，与当前版本  versionValue  相关联。
          manager.getDynamicPropertiesStore().statsByVersion(versionValue, stats);
        }
      }
    }
  }

  private void upgrade(int version, int slotSize) {
    for (ForkBlockVersionEnum versionEnum : ForkBlockVersionEnum.values()) {
      int versionValue = versionEnum.getValue();
      if (versionValue < version && !pass(versionValue)) {
        byte[] stats = manager.getDynamicPropertiesStore().statsByVersion(versionValue);
        if (stats == null || stats.length == 0) {
          stats = new byte[slotSize];
        }
        Arrays.fill(stats, VERSION_UPGRADE);
        manager.getDynamicPropertiesStore().statsByVersion(versionValue, stats);
      }
    }
  }

  //更新区块信息，根据区块版本和见证人信息决定是否升级或降级。
  //synchronized  关键字表示该方法是线程安全的，多个线程不能同时执行这个方法。
  public synchronized void update(BlockCapsule blockCapsule) {
    //从  manager  对象的见证者调度存储中获取当前活跃的见证者列表，并将其存储在  witnesses  变量中。
    List<ByteString> witnesses = manager.getWitnessScheduleStore().getActiveWitnesses();
    //调用  blockCapsule  的  getWitnessAddress()  方法获取当前区块的见证者地址，并将其存储在  witness  变量中。
    ByteString witness = blockCapsule.getWitnessAddress();
    //查找  witnesses  列表中  witness  的索引位置，并将其存储在  slot  变量中。
    int slot = witnesses.indexOf(witness);
    //如果  witness  不在列表中， slot  将为 -1。
    if (slot < 0) {
      //检查  slot  是否小于 0，如果是，表示没有找到对应的见证者，则直接返回，不执行后续操作。
      return;
    }

    //调用  blockCapsule  的方法获取区块头的版本号，并将其存储在  version  变量中。
    int version = blockCapsule.getInstance().getBlockHeader().getRawData().getVersion();
    //检查  version  是否小于某个常量  ENERGY_LIMIT 。如果是，则返回，表示该版本不符合条件。
    if (version < ForkBlockVersionConsts.ENERGY_LIMIT) {
      return;
    }
    //检查动态属性存储中最新的版本是否大于或等于当前版本。
    if (manager.getDynamicPropertiesStore().getLatestVersion() >= version) {
      //如果是，则返回，表示不需要更新。
      return;
    }

    downgrade(version, slot);
    //从动态属性存储中获取与当前版本相关的统计数据，并将其存储在  stats  数组中。
    byte[] stats = manager.getDynamicPropertiesStore().statsByVersion(version);
    //检查  stats  是否为  null  或者其长度是否与  witnesses  列表的大小不一致。
    if (Objects.isNull(stats) || stats.length != witnesses.size()) {
      //如果是，则创建一个新的  stats  数组，其大小与  witnesses  相同。
      stats = new byte[witnesses.size()];
    }

    //调用  pass(version)  方法，检查当前版本是否通过验证。
    if (pass(version)) {
      //如果版本通过验证，调用  upgrade  方法，传入当前版本和  stats  数组的长度，执行升级操作。
      upgrade(version, stats.length);
      //将当前版本保存为最新版本。
      manager.getDynamicPropertiesStore().saveLatestVersion(version);
      return;
    }
    //将  stats  数组中索引为  slot  的位置的值设置为  VERSION_UPGRADE ，表示该见证者进行了升级。
    stats[slot] = VERSION_UPGRADE;
    //将更新后的  stats  数组保存回动态属性存储中，与当前版本相关联。
    manager.getDynamicPropertiesStore().statsByVersion(version, stats);
    //记录一条信息日志，内容包括硬分叉的更新信息、见证者的数量、索引位置、见证者地址和版本号。
    logger.info(
        "Update hard fork: {}, witness size: {}, solt: {}, witness: {}, version: {}.",
        Streams.zip(witnesses.stream(), Stream.of(ArrayUtils.toObject(stats)), Maps::immutableEntry)
            .map(e -> Maps
                .immutableEntry(encode58Check(e.getKey().toByteArray()), e.getValue()))
            .map(e -> Maps
                .immutableEntry(StringUtils.substring(e.getKey(), e.getKey().length() - 4),
                    e.getValue()))
            .collect(Collectors.toList()),
        witnesses.size(),
        slot,
        encode58Check(witness.toByteArray()),
        version);
  }

  //重置分叉控制器的状态，通常在特定条件下调用。
  public synchronized void reset() {
    int size = manager.getWitnessScheduleStore().getActiveWitnesses().size();
    for (ForkBlockVersionEnum versionEnum : ForkBlockVersionEnum.values()) {
      int versionValue = versionEnum.getValue();
      byte[] stats = manager.getDynamicPropertiesStore().statsByVersion(versionValue);
      if (Objects.nonNull(stats) && !pass(versionValue)) {
        stats = new byte[size];
        manager.getDynamicPropertiesStore().statsByVersion(versionValue, stats);
      }
    }
  }

  //获取ForkController的实例。
  public static ForkController instance() {
    return ForkControllerEnum.INSTANCE.getInstance();
  }

  private enum ForkControllerEnum {
    INSTANCE;

    private ForkController instance;

    ForkControllerEnum() {
      instance = new ForkController();
    }

    private ForkController getInstance() {
      return instance;
    }
  }

}
