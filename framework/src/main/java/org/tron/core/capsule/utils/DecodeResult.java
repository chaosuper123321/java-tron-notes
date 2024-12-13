package org.tron.core.capsule.utils;

import java.io.Serializable;
import org.bouncycastle.util.encoders.Hex;

@SuppressWarnings("serial")
public class DecodeResult implements Serializable {
  //解码结果的位置
  private int pos;
  //解码结果的内容。
  private Object decoded;

  //构造函数，初始化解码结果的位置和内容。
  public DecodeResult(int pos, Object decoded) {
    this.pos = pos;
    this.decoded = decoded;
  }

  //获取解码结果的位置。
  public int getPos() {
    return pos;
  }

  //获取解码结果的内容。
  public Object getDecoded() {
    return decoded;
  }

  //将解码结果转换为字符串形式。
  public String toString() {
    return asString(this.decoded);
  }

  //将解码结果转换为字符串形式。
  private String asString(Object decoded) {
    if (decoded instanceof String) {
      return (String) decoded;
    } else if (decoded instanceof byte[]) {
      return Hex.toHexString((byte[]) decoded);
    } else if (decoded instanceof Object[]) {
      String result = "";
      for (Object item : (Object[]) decoded) {
        result += asString(item);
      }
      return result;
    }
    throw new RuntimeException("Not a valid type. Should not occur");
  }
}
