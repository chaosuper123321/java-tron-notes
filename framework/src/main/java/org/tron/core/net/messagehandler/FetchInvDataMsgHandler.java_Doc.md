## Module: FetchInvDataMsgHandler.java
模块名称: FetchInvDataMsgHandler.java

主要目标: 该模块的主要目标是处理接收到的FetchInvDataMessage消息，根据消息中的类型和哈希值获取相应的数据，并进行处理。

关键功能: 
- processMessage(PeerConnection peer, TronMessage msg): 处理接收到的消息，根据消息类型获取对应的数据并发送给对等节点。
- sendPbftCommitMessage(PeerConnection peer, BlockCapsule blockCapsule): 发送PbftCommitMessage消息给对等节点。
- check(PeerConnection peer, FetchInvDataMessage fetchInvDataMsg): 检查接收到的FetchInvDataMessage消息中的哈希值是否有效。

关键变量: 
- epochCache: 缓存数据，存储epoch值和Boolean类型的数据。
- MAX_SIZE: 最大数据大小限制。
- tronNetDelegate, syncService, advService, consensusDelegate: 与其他组件交互的关键变量。

相互依赖性: 该模块与TronNetDelegate、SyncService、AdvService、ConsensusDelegate等组件有交互依赖关系，需要调用它们的方法和数据。

核心 vs. 辅助操作: 核心操作包括处理消息、发送PbftCommitMessage消息，辅助操作包括检查消息有效性。

操作序列: 
1. 接收FetchInvDataMessage消息。
2. 检查消息有效性。
3. 根据消息类型和哈希值获取数据。
4. 处理Block类型数据和Transaction类型数据。
5. 发送消息给对等节点。

性能方面: 在处理大量数据时需要考虑性能问题，如数据大小限制、缓存使用等。

可重用性: 该模块具有一定的可重用性，可以在其他模块中调用相关方法进行数据处理和消息发送。

用法: 该模块用于处理接收到的FetchInvDataMessage消息，根据消息中的类型和哈希值获取相应的数据并发送给对等节点。

假设: 
- 假设消息中的哈希值有效且数据可获取。
- 假设对等节点能够正确接收和处理发送的消息。
## Flow Diagram [via mermaid]
```mermaid
flowchart
  st=>start: Start
  op=>operation: processMessage(PeerConnection peer, TronMessage msg)
  cond=>condition: type == InventoryType.BLOCK?
  subop1=>operation: sendPbftCommitMessage(peer, blockCapsule)
  subop2=>operation: peer.sendMessage(message)
  subop3=>operation: peer.sendMessage(new TransactionsMessage(transactions))
  e=>end: End
  
  st->op->cond
  cond(yes)->subop1->subop2->op
  cond(no)->subop2(right)->subop3->op
```
