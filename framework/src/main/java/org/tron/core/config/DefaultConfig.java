package org.tron.core.config;

import com.alibaba.fastjson.parser.ParserConfig;
import lombok.extern.slf4j.Slf4j;
import org.rocksdb.RocksDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.tron.common.utils.StorageUtils;
import org.tron.core.config.args.Args;
import org.tron.core.db.RevokingDatabase;
import org.tron.core.db.backup.BackupRocksDBAspect;
import org.tron.core.db.backup.NeedBeanCondition;
import org.tron.core.db2.core.SnapshotManager;
import org.tron.core.services.interfaceOnPBFT.RpcApiServiceOnPBFT;
import org.tron.core.services.interfaceOnPBFT.http.PBFT.HttpApiOnPBFTService;
import org.tron.core.services.interfaceOnSolidity.RpcApiServiceOnSolidity;
import org.tron.core.services.interfaceOnSolidity.http.solidity.HttpApiOnSolidityService;

/**
 * 配置Tron区块链的默认设置。
 */
@Slf4j(topic = "app")
@Configuration
@Import(CommonConfig.class)
public class DefaultConfig {

  static {
    RocksDB.loadLibrary();
    ParserConfig.getGlobalInstance().setSafeMode(true);
  }

  @Autowired
  public ApplicationContext appCtx;

  @Autowired
  public CommonConfig commonConfig;

  public DefaultConfig() {
    Thread.setDefaultUncaughtExceptionHandler((t, e) -> logger.error("Uncaught exception", e));
  }

  //创建用于撤销数据库的RevokingDatabase实例。
  @Bean(destroyMethod = "")
  public RevokingDatabase revokingDatabase() {
    try {
      return new SnapshotManager(
          StorageUtils.getOutputDirectoryByDbName("block"));
    } finally {
      logger.info("key-value data source created.");
    }
  }


  //根据是否为Solidity节点返回适当的RpcApiServiceOnSolidity实例。
  @Bean
  public RpcApiServiceOnSolidity getRpcApiServiceOnSolidity() {
    boolean isSolidityNode = Args.getInstance().isSolidityNode();
    if (!isSolidityNode) {
      return new RpcApiServiceOnSolidity();
    }

    return null;
  }

  //根据是否为Solidity节点返回适当的HttpApiOnSolidityService实例。
  @Bean
  public HttpApiOnSolidityService getHttpApiOnSolidityService() {
    boolean isSolidityNode = Args.getInstance().isSolidityNode();
    if (!isSolidityNode) {
      return new HttpApiOnSolidityService();
    }

    return null;
  }

  //根据是否为Solidity节点返回适当的RpcApiServiceOnPBFT实例。
  @Bean
  public RpcApiServiceOnPBFT getRpcApiServiceOnPBFT() {
    boolean isSolidityNode = Args.getInstance().isSolidityNode();
    if (!isSolidityNode) {
      return new RpcApiServiceOnPBFT();
    }

    return null;
  }

  //根据是否为Solidity节点返回适当的HttpApiOnPBFTService实例。
  @Bean
  public HttpApiOnPBFTService getHttpApiOnPBFTService() {
    boolean isSolidityNode = Args.getInstance().isSolidityNode();
    if (!isSolidityNode) {
      return new HttpApiOnPBFTService();
    }

    return null;
  }

  //创建用于备份RocksDB的BackupRocksDBAspect实例。
  @Bean
  @Conditional(NeedBeanCondition.class)
  public BackupRocksDBAspect backupRocksDBAspect() {
    return new BackupRocksDBAspect();
  }
}
