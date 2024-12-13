/*
 * Copyright (c) [2016] [ <ether.camp> ]
 * This file is part of the ethereumJ library.
 *
 * The ethereumJ library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The ethereumJ library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with the ethereumJ library. If not, see <http://www.gnu.org/licenses/>.
 */

package org.tron.common.backup.socket;

import java.net.InetSocketAddress;
import org.tron.common.backup.message.Message;

public class UdpEvent {
  //表示与 UDP 事件关联的消息。
  private Message message;
  //表示 UDP 事件的 InetSocketAddress。
  private InetSocketAddress address;

  //使用消息和 InetSocketAddress 初始化 UdpEvent 对象
  public UdpEvent(Message message, InetSocketAddress address) {
    this.message = message;
    this.address = address;
  }

  //检索与 UdpEvent 关联的消息。
  public Message getMessage() {
    return message;
  }

  //为 UdpEvent 设置新消息。
  public void setMessage(Message message) {
    this.message = message;
  }

  //检索与 UdpEvent 关联的 InetSocketAddress。
  public InetSocketAddress getAddress() {
    return address;
  }

  //为 UdpEvent 设置一个新的 InetSocketAddress。
  public void setAddress(InetSocketAddress address) {
    this.address = address;
  }
}
