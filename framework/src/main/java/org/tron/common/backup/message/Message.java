package org.tron.common.backup.message;

import org.apache.commons.lang3.ArrayUtils;
import org.tron.common.parameter.CommonParameter;
import org.tron.common.utils.ByteArray;
import org.tron.common.utils.Sha256Hash;
import org.tron.core.exception.P2pException;
import org.tron.p2p.discover.Node;
import org.tron.protos.Discover.Endpoint;

/**
 * 创建Message对象并初始化。
 * 解析消息数据。
 * 根据消息类型创建对应的消息对象。
 * 获取消息ID、发送数据等。
 */
public abstract class Message {

  //消息类型
  protected UdpMessageTypeEnum type;
  //消息数据
  protected byte[] data;

  //构造函数，初始化消息类型和数据。
  public Message(UdpMessageTypeEnum type, byte[] data) {
    this.type = type;
    this.data = data;
  }

  //根据Endpoint创建Node对象。
  public static Node getNode(Endpoint endpoint) {
    Node node = new Node(endpoint.getNodeId().toByteArray(),
        ByteArray.toStr(endpoint.getAddress().toByteArray()),
        ByteArray.toStr(endpoint.getAddressIpv6().toByteArray()), endpoint.getPort());
    return node;
  }

  //解析字节数组，根据消息类型创建对应的消息对象。
  public static Message parse(byte[] encode) throws Exception {
    byte type = encode[0];
    byte[] data = ArrayUtils.subarray(encode, 1, encode.length);
    switch (UdpMessageTypeEnum.fromByte(type)) {
      case BACKUP_KEEP_ALIVE:
        return new KeepAliveMessage(data);
      default:
        throw new P2pException(P2pException.TypeEnum.NO_SUCH_MESSAGE, "type=" + type);
    }
  }

  //获取消息类型。
  public UdpMessageTypeEnum getType() {
    return this.type;
  }

  //获取消息数据。
  public byte[] getData() {
    return this.data;
  }

  //获取要发送的数据。
  public byte[] getSendData() {
    return ArrayUtils.add(this.data, 0, type.getType());
  }

  //获取消息的ID。
  public Sha256Hash getMessageId() {
    return Sha256Hash.of(CommonParameter.getInstance().isECKeyCryptoEngine(), getData());
  }

  //抽象方法，获取消息发送者。
  public abstract Node getFrom();

  //抽象方法，获取消息时间戳。
  public abstract long getTimestamp();

  @Override
  public String toString() {
    return "[Message Type: " + getType() + ", len: " + (data == null ? 0 : data.length) + "]";
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  public int hashCode() {
    return getMessageId().hashCode();
  }
}
