## Module: TronNetService.java
模块：TronNetService.java

主要目标：该模块的目的是实现与Tron网络相关的服务功能。

关键功能：包括启动网络服务、关闭网络服务、获取对等节点、消息广播、快速广播交易、检测IPv4地址等。

关键变量：P2pConfig、P2pService、advService、syncService、peerStatusCheck、transactionsMsgHandler、fetchBlockService、nodePersistService、tronStatsManager、relayService、effectiveCheckService等。

相互依赖：该模块与P2pConfig、P2pService、AdvService、SyncService等其他组件之间存在相互作用。

核心与辅助操作：启动网络服务、关闭网络服务、消息广播为核心操作，其他为辅助操作。

操作序列：首先初始化各项服务，然后启动网络服务，注册事件处理器，最后关闭网络服务。

性能方面：需要考虑网络连接数、最大连接数、最小连接数等性能指标。

可重用性：该模块具有良好的适应性，可在不同场景下重复使用。

用法：用于启动、关闭Tron网络服务，并处理与网络通信相关的操作。

假设：假设网络服务能够正常启动和关闭，能够获取对等节点信息，并能够正确处理消息广播和交易快速广播。
## Flow Diagram [via mermaid]
```mermaid
flowchart
    st=>start: Start
    e=>end: End
    op1=>operation: TronNetService.java
    op2=>operation: P2pService
    op3=>operation: AdvService
    op4=>operation: SyncService
    op5=>operation: PeerStatusCheck
    op6=>operation: TransactionsMsgHandler
    op7=>operation: FetchBlockService
    op8=>operation: NodePersistService
    op9=>operation: TronStatsManager
    op10=>operation: RelayService
    op11=>operation: EffectiveCheckService
    op12=>operation: PeerManager
    op13=>operation: P2pEventHandlerImpl

    st->op1->op2->op3->op4->op5->op6->op7->op8->op9->op12->op10->op11->e
    op1->op13
```
