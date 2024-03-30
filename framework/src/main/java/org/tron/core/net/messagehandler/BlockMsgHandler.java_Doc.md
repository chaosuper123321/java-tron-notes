## Module: BlockMsgHandler.java
模块：BlockMsgHandler.java

主要目标：处理区块消息的相关功能。

关键功能：包括处理消息、检查区块、处理区块等方法。

关键变量：maxBlockSize、fastForward、blockMessage等。

相互依赖：与RelayService、TronNetDelegate、AdvService、SyncService、FetchBlockService等组件有交互。

核心操作 vs. 辅助操作：核心操作包括处理消息、处理区块；辅助操作包括检查区块。

操作序列：接收区块消息，检查区块，处理区块，广播消息。

性能方面：考虑区块大小、时间延迟等性能因素。

可重用性：可以适应不同场景下的区块处理需求。

用途：用于处理区块消息传输和验证。

假设：假设区块大小不超过限制，时间间隔合理等。
## Flow Diagram [via mermaid]
```mermaid
graph TD
A[BlockMsgHandler.java] -->|processMessage| B(BlockMessage)
B -->|check| C{peer}
C -->|containsKey| D{msg.getBlockId}
D -->|&&| E{peer.getAdvInvRequest}
E -->|containsKey| F{item}
F -->|logger.error| G{throw P2pException}
G -->|BAD_MESSAGE| H{no request}
H -->|processBlock| I(BlockCapsule)
I -->|tronNetDelegate.containBlock| J{block.getParentBlockId}
J -->|logger.warn| K{syncService.startSync}
K -->|return| L( )
J -->|block.getNum < headNum| M{tronNetDelegate.getHeadBlockId.getNum}
M -->|logger.warn| N( )
M -->|tronNetDelegate.validBlock| O{flag}
O -->|broadcast| P(BlockMessage)
P -->|tronNetDelegate.processBlock| Q{flag}
Q -->|broadcast| R(BlockMessage)
R -->|witnessProductBlockService.validWitnessProductTwoBlock| S(Block)
S -->|forEach| T{p.getAdvInvReceive}
T -->|getIfPresent| U{blockId}
U -->|setBlockBothHave| V{blockId}
R -->|tronNetDelegate.getActivePeer| W{p}
W -->|forEach| X{p.getAdvInvReceive}
X -->|getIfPresent| Y{blockId}
Y -->|setBlockBothHave| Z{blockId}
Q -->|catch| AA{logger.warn}
AA -->|logger.warn| BB( )
AA -->|broadcast| CC(BlockMessage)
CC -->|fastForward| DD{relayService.broadcast}
DD -->|else| EE{advService.broadcast}
```
