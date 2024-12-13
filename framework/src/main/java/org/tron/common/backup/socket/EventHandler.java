package org.tron.common.backup.socket;

//处理激活通道和处理UDP事件。
public interface EventHandler {

  //用于激活通道。
  void channelActivated();

  //用于处理UDP事件。
  void handleEvent(UdpEvent event);

}
