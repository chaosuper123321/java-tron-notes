## Module: HelloMessage.java
模块名: HelloMessage.java

主要目标: 这个模块的目的是处理和创建Hello消息，用于节点之间的握手通信。

关键功能: 
- `HelloMessage(byte type, byte[] rawData)`: 从原始数据解析Hello消息。
- `HelloMessage(byte[] data)`: 从数据解析Hello消息。
- `HelloMessage(Node from, long timestamp, ChainBaseManager chainBaseManager)`: 创建包含节点信息和区块信息的Hello消息。
- `setHelloMessage(Protocol.HelloMessage helloMessage)`: 设置Hello消息。
- `getVersion()`: 获取Hello消息的版本号。
- `getNodeType()`: 获取节点类型。
- `getLowestBlockNum()`: 获取最低区块号。
- `getTimestamp()`: 获取时间戳。
- 其他方法用于获取节点、区块ID等信息。

关键变量: 
- `helloMessage`: 存储Hello消息的协议对象。

相互依赖: 
- 与`ChainBaseManager`交互以获取区块信息和节点类型。
- 与其他系统组件交互以解析和创建Hello消息。

核心与辅助操作: 
- 核心操作包括创建和解析Hello消息，获取关键信息。
- 辅助操作包括设置消息、验证消息有效性等。

操作序列: 
- 创建Hello消息时，需要提供节点信息、时间戳和区块信息。

性能方面: 
- 需要考虑消息解析和创建的性能，以及与其他系统组件的交互性能。

可重用性: 
- 该模块可以在不同的节点间通信场景中重复使用。

用法: 
- 可以通过调用不同方法来获取Hello消息的各种信息。

假设: 
- 假设输入的数据符合Hello消息的协议格式。
## Flow Diagram [via mermaid]
```mermaid
flowchart TD
    A[HelloMessage.java] -->|imports| B[org.tron.core.net.message.handshake]
    A -->|imports| C[com.google.protobuf.ByteString]
    A -->|imports| D[lombok.Getter]
    A -->|imports| E[org.apache.commons.lang3.StringUtils]
    A -->|imports| F[org.tron.common.utils.ByteArray]
    A -->|imports| G[org.tron.common.utils.StringUtil]
    A -->|imports| H[org.tron.core.ChainBaseManager]
    A -->|imports| I[org.tron.core.capsule.BlockCapsule]
    A -->||imports| J[org.tron.core.config.args.Args]
    A -->|imports| K[org.tron.core.net.message.MessageTypes]
    A -->|imports| L[org.tron.core.net.message.TronMessage]
    A -->|imports| M[org.tron.p2p.discover.Node]
    A -->|imports| N[org.tron.program.Version]
    A -->|imports| O[org.tron.protos.Discover.Endpoint]
    A -->|imports| P[org.tron.protos.Protocol]
    A -->|imports| Q[org.tron.protos.Protocol.HelloMessage.Builder]
    B -->|extends| L
    B -->|has| P.HelloMessage
    L -->|has| type
    L -->|has| rawData
    L -->|has| data
    L -->|constructor| L(MessageTypes.P2P_HELLO.asByte, data)
    L -->|constructor| L(type, rawData)
    L -->|constructor| L(Node from, timestamp, chainBaseManager)
    L -->|has| version
    L -->|has| nodeType
    L -->|has| lowestBlockNum
    L -->|has| from
    L -->|has| genesisBlockId
    L -->|has| solidBlockId
    L -->|has| headBlockId
    L -->|has| answerMessage
    L -->|method| toString
    L -->|method| getInstance
    L -->|method| valid
    L -->|static method| getEndpointFromNode
```
