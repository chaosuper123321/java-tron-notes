package org.tron.common.bloom;

import com.google.protobuf.ByteString;
import java.util.Arrays;
import java.util.Iterator;
import org.tron.common.crypto.Hash;
import org.tron.common.utils.ByteArray;
import org.tron.common.utils.ByteUtil;
import org.tron.core.capsule.TransactionRetCapsule;
import org.tron.protos.Protocol.TransactionInfo;
import org.tron.protos.Protocol.TransactionInfo.Log;

/**
 * 该模块的目的是提供一个布隆过滤器的实现，用于快速检查一个元素是否可能存在于一个集合中。
 *
 * 布隆过滤器是一种空间效率高但有一定误判率的数据结构，常用于区块链等技术中以减少不必要的数据检索。
 *
 * 核心 vs. 辅助操作：创建和修改布隆过滤器的操作（如create和or方法）是核心操作，而matches和copy等方法可视为辅助操作，用于检测和复制。
 *
 * 操作序列：通常，首先通过create方法创建布隆过滤器，然后使用or方法添加元素或合并其他布隆过滤器，最后可以使用matches方法进行匹配检查。
 *
 * 性能方面：布隆过滤器以牺牲准确性（存在一定的误报率）为代价，大幅度减少了内存使用。性能考量主要集中在如何平衡误报率与空间效率。
 *
 */
public class Bloom {
  //BLOOM_BIT_SIZE：布隆过滤器的位大小。
  public static final int BLOOM_BIT_SIZE = 2048;
  //BLOOM_BYTE_SIZE：布隆过滤器的字节大小。
  public static final int BLOOM_BYTE_SIZE = BLOOM_BIT_SIZE / 8;
  private static final int STEPS_8 = 8;
  private static final int ENSURE_BYTE = 255;
  private static final int LOW_3_BITS = getLowBits(BLOOM_BIT_SIZE);
  //data：存储布隆过滤器数据的字节数组。
  private byte[] data = new byte[BLOOM_BYTE_SIZE];
  //构造函数，初始化布隆过滤器。
  public Bloom() {
  }
  //带数据的构造函数，用于创建具有给定字节数据的布隆过滤器。
  public Bloom(byte[] data) {
    if (data.length != this.data.length) {
      throw new RuntimeException(
          "input data length is not equal to Bloom size " + this.data.length);
    }
    this.data = data;
  }

  //get several low bit. 512 -> 0b1, 1024 -> 0b11, 2048 -> 0b111, 4096-> 0b1111
  public static int getLowBits(int bloomBitSize) {
    return ENSURE_BYTE >> (16 + 1 - Integer.toBinaryString(bloomBitSize).length());
  }

  //静态方法，根据给定的字节数组创建一个新的布隆过滤器实例。
  public static Bloom create(byte[] toBloom) {

    int mov1 =
        (((toBloom[0] & ENSURE_BYTE) & (LOW_3_BITS)) << STEPS_8) + ((toBloom[1]) & ENSURE_BYTE);
    int mov2 =
        (((toBloom[2] & ENSURE_BYTE) & (LOW_3_BITS)) << STEPS_8) + ((toBloom[3]) & ENSURE_BYTE);
    int mov3 =
        (((toBloom[4] & ENSURE_BYTE) & (LOW_3_BITS)) << STEPS_8) + ((toBloom[5]) & ENSURE_BYTE);

    byte[] data = new byte[BLOOM_BYTE_SIZE];
    Bloom bloom = new Bloom(data);

    ByteUtil.setBit(data, mov1, 1);
    ByteUtil.setBit(data, mov2, 1);
    ByteUtil.setBit(data, mov3, 1);

    return bloom;
  }

  //静态方法，根据交易回执创建布隆过滤器。
  public static Bloom createBloom(TransactionRetCapsule transactionRetCapsule) {
    if (transactionRetCapsule == null) {
      return null;
    }
    Iterator<TransactionInfo> it =
        transactionRetCapsule.getInstance().getTransactioninfoList().iterator();
    Bloom blockBloom = null;

    while (it.hasNext()) {
      TransactionInfo transactionInfo = it.next();
      if (transactionInfo == null || transactionInfo.getLogCount() == 0) {
        continue;
      }

      if (blockBloom == null) {
        blockBloom = new Bloom();
      }

      for (Log log : transactionInfo.getLogList()) {
        //log.address doesn't have "41" ahead
        Bloom bloom = Bloom.create(Hash.sha3(log.getAddress().toByteArray()));
        blockBloom.or(bloom);

        for (ByteString topic : log.getTopicsList()) {
          bloom = Bloom.create(Hash.sha3(topic.toByteArray()));
          blockBloom.or(bloom);
        }
      }
    }

    return blockBloom;
  }

  //执行布隆过滤器的OR操作，用于合并另一个布隆过滤器的数据。
  public void or(Bloom bloom) {
    for (int i = 0; i < data.length; ++i) {
      data[i] |= bloom.data[i];
    }
  }

  //检查两个布隆过滤器是否匹配。
  public boolean matches(Bloom topicBloom) {
    Bloom copy = copy();
    copy.or(topicBloom);
    return this.equals(copy);
  }

  public byte[] getData() {
    return data;
  }

  //复制当前布隆过滤器实例。
  public Bloom copy() {
    return new Bloom(Arrays.copyOf(getData(), getData().length));
  }

  @Override
  public String toString() {
    return ByteArray.toHexString(data);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Bloom bloom = (Bloom) o;

    return Arrays.equals(data, bloom.data);
  }

  @Override
  public int hashCode() {
    return data != null ? Arrays.hashCode(data) : 0;
  }
}
