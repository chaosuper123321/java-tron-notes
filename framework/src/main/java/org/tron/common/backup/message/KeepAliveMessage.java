package org.tron.common.backup.message;

import static org.tron.common.backup.message.UdpMessageTypeEnum.BACKUP_KEEP_ALIVE;

import org.tron.p2p.discover.Node;
import org.tron.protos.Discover;

//维持通信的活跃性，确保备份消息的持续传输。
public class KeepAliveMessage extends Message {

  private Discover.BackupMessage backupMessage;

  //从输入数据中解析出备份消息，并初始化KeepAliveMessage对象。
  public KeepAliveMessage(byte[] data) throws Exception {
    super(BACKUP_KEEP_ALIVE, data);
    backupMessage = Discover.BackupMessage.parseFrom(data);
  }

  //根据传入的标志和优先级创建备份消息，并初始化KeepAliveMessage对象。
  public KeepAliveMessage(boolean flag, int priority) {
    super(BACKUP_KEEP_ALIVE, null);
    backupMessage = Discover.BackupMessage.newBuilder().setFlag(flag).setPriority(priority).build();
    data = backupMessage.toByteArray();
  }

  //获取备份消息中的标志信息。
  public boolean getFlag() {
    return backupMessage.getFlag();
  }

  //获取备份消息中的优先级信息。
  public int getPriority() {
    return backupMessage.getPriority();
  }

  //获取时间戳信息。
  @Override
  public long getTimestamp() {
    return 0;
  }

  //获取消息发送方信息。
  @Override
  public Node getFrom() {
    return null;
  }
}
