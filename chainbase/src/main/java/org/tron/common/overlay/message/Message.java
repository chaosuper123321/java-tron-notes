package org.tron.common.overlay.message;

import static org.tron.core.exception.P2pException.TypeEnum.PROTOBUF_ERROR;

import com.google.protobuf.CodedInputStream;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.lang.reflect.Field;
import java.util.Arrays;
import lombok.Setter;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;
import org.tron.common.parameter.CommonParameter;
import org.tron.common.utils.Sha256Hash;
import org.tron.core.exception.P2pException;
import org.tron.core.net.message.MessageTypes;
import org.tron.core.store.DynamicPropertiesStore;

/**
 * 定义一个抽象的消息类，用于在网络层之间传递消息，支持数据打包、消息类型定义、消息ID生成等功能。
 *
 */
public abstract class Message {

  protected static final Logger logger = LoggerFactory.getLogger("Message");
  // https://developers.google.com/protocol-buffers/docs/proto3#unknowns
  // https://github.com/protocolbuffers/protobuf/issues/272
  private static final Field field = ReflectionUtils
      .findField(CodedInputStream.class, "shouldDiscardUnknownFields");
  @Setter
  private static DynamicPropertiesStore dynamicPropertiesStore;

  static {
    ReflectionUtils.makeAccessible(field);
  }

  protected byte[] data;
  protected byte type;

  public Message() {
  }

  public Message(byte[] packed) {
    this.data = packed;
  }

  public Message(byte type, byte[] packed) {
    this.type = type;
    this.data = packed;
  }

  //比较两个字节数组是否相等，用于校验数据完整性。
  public static void compareBytes(byte[] src, byte[] dest) throws P2pException {
    if (src.length != dest.length) {
      throw new P2pException(PROTOBUF_ERROR, PROTOBUF_ERROR.getDesc());
    }
  }

  //根据字节数组数据获取CodedInputStream实例，支持过滤未知字段。
  public static CodedInputStream getCodedInputStream(byte[] data) {
    CodedInputStream codedInputStream = CodedInputStream.newInstance(data);
    if (isFilter()) {
      ReflectionUtils.setField(field, codedInputStream, true);
    }
    return codedInputStream;
  }

  public static boolean isFilter() {
    return dynamicPropertiesStore.getAllowProtoFilterNum() == 1;
  }

  //获取发送数据的ByteBuf格式。
  public ByteBuf getSendData() {
    return Unpooled.wrappedBuffer(ArrayUtils.add(this.getData(), 0, type));
  }

  //获取发送数据的字节数组格式。
  public byte[] getSendBytes() {
    return ArrayUtils.add(this.getData(), 0, type);
  }

  //生成消息的ID，用于消息追踪和识别。
  public Sha256Hash getMessageId() {
    return Sha256Hash.of(CommonParameter.getInstance().isECKeyCryptoEngine(),
        getData());
  }

  //获取消息的数据内容。
  public byte[] getData() {
    return this.data;
  }

  //获取消息的类型。
  public MessageTypes getType() {
    return MessageTypes.fromByte(this.type);
  }

  //抽象方法，定义了获取响应消息类的类型。
  public abstract Class<?> getAnswerMessage();

  @Override
  public String toString() {
    return "type: " + getType() + "\n";
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(data);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Message)) {
      return false;
    }
    Message message = (Message) o;
    return Arrays.equals(data, message.data);
  }

}