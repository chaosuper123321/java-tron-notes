## Module: PbftMessage.java
- **模块名称**：PbftMessage.java

- **主要目标**：该模块的目的是定义和实现PBFT（实用拜占庭容错）消息的创建和处理逻辑，以支持区块链网络中的共识机制。

- **关键功能**：
  - `prePrepareBlockMsg`和`fullNodePrePrepareBlockMsg`：用于构建预准备阶段的区块消息。
  - `prePrepareSRLMsg`和`fullNodePrePrepareSRLMsg`：用于构建预准备阶段的SRL（特定资源列表）消息。
  - `buildPrePareMessage`：构建准备阶段的消息。
  - `buildCommitMessage`：构建提交阶段的消息。
  - 私有方法`buildCommon`和`buildFullNodeCommon`：辅助方法，用于构建消息的通用部分。

- **关键变量**：
  - `pbftMessage`：存储PBFT消息的实例。
  - `DataType`和`MsgType`：枚举类型，指定消息的数据类型和消息类型。

- **互相依赖**：该模块依赖于`BlockCapsule`类来获取区块信息，同时也依赖于`ECKey`类和`Sha256Hash`类来进行数字签名和哈希运算。

- **核心与辅助操作**：
  - 核心操作包括消息的创建和签名过程。
  - 辅助操作包括对消息类型和数据类型的枚举定义，以及私有方法`buildCommon`和`buildFullNodeCommon`的实现。

- **操作序列**：首先，根据消息类型（如预准备、准备、提交）和数据类型（如区块、SRL），构建消息的原始数据。然后，计算数据的哈希值，并使用矿工的私钥进行签名。最后，将签名和原始数据组合成最终的PBFT消息。

- **性能方面**：性能考虑包括数字签名和哈希运算的计算成本，这可能会影响消息构建的效率。

- **可重用性**：该模块设计为可重用，可以在不同的区块链项目中实现PBFT共识机制，只需少量修改即可适应不同的应用场景。

- **使用**：在区块链网络中，节点使用此模块来构建和处理PBFT消息，以达成网络中的共识。

- **假设**：假设所有参与共识的矿工都拥有有效的私钥，且区块链网络能够安全可靠地传输消息。
## Flow Diagram [via mermaid]
```mermaid
flowchart TB
    subgraph creation [Message Creation]
        direction TB
        buildCommon(buildCommon(DataType, ByteString, BlockCapsule, long, long, Miner)) --> pbftMessageCommon[PbftMessage]
        buildFullNodeCommon(buildFullNodeCommon(DataType, ByteString, BlockCapsule, long, long)) --> pbftMessageFullNode[PbftMessage]
    end

    subgraph preparation [Preparation Messages]
        direction TB
        prePrepareBlockMsg(prePrepareBlockMsg(BlockCapsule, long, Miner)) --> buildCommon
        prePrepareSRLMsg(prePrepareSRLMsg(BlockCapsule, List<ByteString>, long, Miner)) --> buildCommon
        fullNodePrePrepareBlockMsg(fullNodePrePrepareBlockMsg(BlockCapsule, long)) --> buildFullNodeCommon
        fullNodePrePrepareSRLMsg(fullNodePrePrepareSRLMsg(BlockCapsule, List<ByteString>, long)) --> buildFullNodeCommon
    end

    subgraph capsule [Message Capsule]
        direction TB
        buildPrePareMessage(buildPrePareMessage(Miner)) --> buildMessageCapsule[buildMessageCapsule(MsgType, Miner)]
        buildCommitMessage(buildCommitMessage(Miner)) --> buildMessageCapsule
    end

    classDef function fill:#f9f,stroke:#333,stroke-width:2px;
    classDef module fill:#bbf,stroke:#333,stroke-width:2px;
    class preparation,module;
    class buildCommon,function;
    class buildFullNodeCommon,function;
```
