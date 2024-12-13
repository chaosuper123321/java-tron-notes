package org.tron.common.backup.message;

import java.util.HashMap;
import java.util.Map;

/**
 * 定义UDP消息类型枚举。
 * 初始化字节值映射。
 * 实现根据字节值返回相应的消息类型枚举。
 */
public enum UdpMessageTypeEnum {
  //定义备份保持活跃的消息类型。
  BACKUP_KEEP_ALIVE((byte) 0x05),
  //定义未知消息类型。
  UNKNOWN((byte) 0xFF);


  private static final Map<Byte, UdpMessageTypeEnum> intToTypeMap = new HashMap<>();

  static {
    for (UdpMessageTypeEnum value : values()) {
      intToTypeMap.put(value.type, value);
    }
  }

  //用于存储消息类型的字节值。
  private final byte type;

  UdpMessageTypeEnum(byte type) {
    this.type = type;
  }

  //根据字节类型返回相应的UDP消息类型枚举。
  public static UdpMessageTypeEnum fromByte(byte type) {
    UdpMessageTypeEnum typeEnum = intToTypeMap.get(type);
    return typeEnum == null ? UNKNOWN : typeEnum;
  }

  //获取消息类型的字节值。
  public byte getType() {
    return type;
  }
}
