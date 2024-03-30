## Module: HandshakeService.java
模块：HandshakeService.java

主要目标：该模块的主要目标是处理握手过程，确保节点之间建立有效的连接。

关键功能：主要方法/功能包括：
- startHandshake(PeerConnection peer): 开始握手过程，发送Hello消息。
- processHelloMessage(PeerConnection peer, HelloMessage msg): 处理收到的Hello消息，验证消息内容并建立连接。

关键变量：关键变量包括relayService、effectiveCheckService、chainBaseManager等。

相互依赖性：该模块与RelayService、EffectiveCheckService、ChainBaseManager等其他系统组件进行交互。

核心与辅助操作：主要操作是处理Hello消息的发送和接收，辅助操作包括连接管理和性能优化。

操作序列：操作序列包括开始握手、处理Hello消息、验证消息内容、建立连接等步骤。

性能方面：性能考虑主要包括消息处理效率和连接稳定性。

可重用性：该模块具有良好的可重用性，可用于不同节点之间的握手过程。

用法：主要用于节点之间建立连接时的握手过程。

假设：假设模块在处理Hello消息时能够正确验证消息内容，确保连接的准确性和稳定性。
## Flow Diagram [via mermaid]
```mermaid
flowchart
    st=>start: Start
    op1=>operation: startHandshake(PeerConnection peer)
    op2=>operation: processHelloMessage(PeerConnection peer, HelloMessage msg)
    e=>end: End

    st->op1
    op1->op2
    op2->e
```
