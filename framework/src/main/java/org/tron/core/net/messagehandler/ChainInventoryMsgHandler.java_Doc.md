## Module: ChainInventoryMsgHandler.java
模块：ChainInventoryMsgHandler.java

主要目标：该模块的主要目标是处理链库存消息，包括同步区块链数据。

关键功能：主要方法和功能包括：
- processMessage：处理接收到的链库存消息，更新对等节点状态和同步数据。
- check：检查接收到的链库存消息的有效性，包括验证区块ID的连续性和一致性。

关键变量：重要变量包括blockIdWeGet、syncFetchBatchNum、peer、blockIds等。

相互依赖：该模块与TronNetDelegate、SyncService等系统组件相互交互，通过这些组件实现区块链数据同步。

核心与辅助操作：核心操作是处理链库存消息并同步数据，辅助操作包括检查消息有效性和更新节点状态。

操作序列：模块首先检查消息有效性，然后处理消息内容，最后更新节点状态和同步数据。

性能考虑：在处理大量区块数据时，可能会影响性能，需要注意数据同步的效率和资源利用。

可重用性：该模块可以适应不同的区块链网络环境，可重用于不同的区块链同步场景。

用法：ChainInventoryMsgHandler模块用于处理接收到的链库存消息，更新节点状态并同步区块链数据。

假设：模块假设接收到的链库存消息是有效的，并且节点之间可以正常通信和同步数据。
## Flow Diagram [via mermaid]
```mermaid
flowchart
    st=>start: Start
    op=>operation: processMessage(PeerConnection peer, TronMessage msg)
    op2=>operation: check(PeerConnection peer, ChainInventoryMessage msg)
    e=>end: End

    st->op->op2
    op2->op
    op->e
```
