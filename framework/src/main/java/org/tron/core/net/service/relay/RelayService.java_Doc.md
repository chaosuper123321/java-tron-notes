## Module: RelayService.java
模块名称：RelayService.java

主要目标：RelayService模块的主要目标是实现中继服务，用于处理网络中继相关的功能。

关键功能：主要方法/函数包括init()、close()、fillHelloMessage()、checkHelloMessage()、connect()、disconnect()、getNextWitnesses()、broadcast()等，分别用于初始化服务、关闭服务、填充Hello消息、检查Hello消息、连接节点、断开连接、获取下一个见证人等操作。

关键变量：关键变量包括chainBaseManager、tronNetDelegate、ctx、manager、witnessScheduleStore、backupManager、executorService、parameter、fastForwardNodes、witnessAddress、keySize、maxFastForwardNum等。

相互依赖：RelayService模块与ChainBaseManager、TronNetDelegate、ApplicationContext等系统组件之间存在相互依赖关系，通过依赖注入实现交互。

核心vs.辅助操作：核心操作包括连接、断开连接、填充Hello消息、检查Hello消息等，而辅助操作则包括获取下一个见证人、广播消息等。

操作序列：模块的操作序列包括初始化服务、定期执行连接/断开连接操作、处理Hello消息、广播消息等流程。

性能方面：在性能方面，模块使用定时任务、异步处理等方式提高系统性能。

可重用性：RelayService模块具有一定的可重用性，可根据需求进行适当调整和扩展以满足不同场景的需求。

使用方式：RelayService模块通过调用init()方法进行初始化，通过调用其他方法实现中继服务的各项功能。

假设：模块假设参数设置正确、网络连接正常、见证人信息有效等。
## Flow Diagram [via mermaid]
```mermaid
flowchart
    st=>start: Start
    e=>end: End
    op1=>operation: init
    op2=>operation: close
    op3=>operation: fillHelloMessage
    op4=>operation: checkHelloMessage
    op5=>operation: isActiveWitness
    op6=>operation: connect
    op7=>operation: disconnect
    op8=>operation: getNextWitnesses
    op9=>operation: broadcast
    
    st->op1->op2->op3->op4->op5->op6->op7->op8->op9->e
```
