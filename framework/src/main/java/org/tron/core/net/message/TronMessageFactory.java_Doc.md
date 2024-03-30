## Module: TronMessageFactory.java
模块名称：TronMessageFactory.java

主要目标：该模块的主要目标是创建不同类型的消息对象。

关键功能：主要方法/函数及其作用包括：
- create(byte[] data)：根据传入的字节数组数据创建消息对象。
- create(byte type, byte[] packed)：根据消息类型和打包数据创建具体的消息对象。

关键变量：重要的变量包括DATA_LEN、isException、type、rawData、receivedTypes。

相互依赖性：该模块与其他系统组件的交互主要体现在创建不同类型消息对象的过程中。

核心与辅助操作：核心操作是根据消息类型创建消息对象，辅助操作包括异常处理和计数器递增。

操作序列：操作序列包括解析数据、判断消息类型、根据类型创建相应消息对象。

性能方面：在异常情况下会增加网络错误协议计数器，需要考虑性能损耗。

可重用性：该模块具有良好的可重用性，可以根据不同的消息类型创建相应的消息对象。

用法：用于在Tron网络中处理不同类型的消息，根据消息类型创建相应的消息对象。

假设：假设传入的数据格式符合预期，否则可能会抛出异常。
## Flow Diagram [via mermaid]
```mermaid
graph TD
    A[TronMessageFactory.java] -->|uses| B[ArrayUtils]
    A -->|uses| C[PbftMessage]
    A -->|uses| D[P2pException]
    A -->|uses| E[MetricsKey]
    A -->|uses| F[MetricsUtil]
    A -->|uses| G[BlockMessage]
    A -->|uses| H[FetchInvDataMessage]
    A -->|uses| I[InventoryMessage]
    A -->|uses| J[TransactionMessage]
    A -->|uses| K[TransactionsMessage]
    A -->|uses| L[DisconnectMessage]
    A -->|uses| M[HelloMessage]
    A -->|uses| N[PingMessage]
    A -->|uses| O[PongMessage]
    A -->|uses| P[PbftCommitMessage]
    A -->|uses| Q[ChainInventoryMessage]
    A -->||uses| R[SyncBlockChainMessage]
```
