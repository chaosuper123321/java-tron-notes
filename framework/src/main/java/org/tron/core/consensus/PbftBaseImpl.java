package org.tron.core.consensus;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tron.consensus.base.PbftInterface;
import org.tron.consensus.pbft.message.PbftBaseMessage;
import org.tron.core.capsule.BlockCapsule;
import org.tron.core.db.Manager;
import org.tron.core.exception.BadItemException;
import org.tron.core.exception.ItemNotFoundException;
import org.tron.core.net.peer.PeerConnection;
import org.tron.core.net.peer.PeerManager;

/**
 * 实现PbftInterface接口，提供一些共识算法的基础功能。
 */
@Component
public class PbftBaseImpl implements PbftInterface {

  //管理器对象，用于管理区块链数据。
  @Autowired
  private Manager manager;

  //检查节点是否正在同步区块链数据。
  @Override
  public boolean isSyncing() {
    List<PeerConnection> peers = PeerManager.getPeers();
    if (peers.isEmpty()) {
      return true;
    }
    AtomicBoolean result = new AtomicBoolean(false);
    peers.forEach(peerConnection -> {
      if (peerConnection.isNeedSyncFromPeer()) {
        result.set(true);
        return;
      }
    });
    return result.get();
  }

  //转发PbftBaseMessage消息给其他节点。
  @Override
  public void forwardMessage(PbftBaseMessage message) {
    //存储节点连接信息的列表。
    List<PeerConnection> peers = PeerManager.getPeers();
    if (peers.isEmpty()) {
      return;
    }
    peers.forEach(peerConnection -> {
      peerConnection.sendMessage(message);
    });
  }

  //获取特定高度的区块。
  @Override
  public BlockCapsule getBlock(long blockNum) throws BadItemException, ItemNotFoundException {
    return manager.getChainBaseManager().getBlockByNum(blockNum);
  }
}