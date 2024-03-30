## Module: MarketPairToPriceStore.java
- **模块名称**：MarketPairToPriceStore.java

- **主要目标**：该模块用于存储特定代币对的首个价格键。键是由卖方ID和买方ID组合而成，值是由卖方ID、买方ID、卖方数量和买方数量组成。

- **关键功能**：
  - `get(byte[] key)`：根据键获取存储的值。
  - `getPriceNum(byte[] key)`：获取价格数量。
  - `getPriceNum(byte[] sellTokenId, byte[] buyTokenId)`：根据卖方和买方的代币ID获取价格数量。
  - `setPriceNum(byte[] key, long number)`：设置价格数量。
  - `setPriceNum(byte[] sellTokenId, byte[] buyTokenId, long number)`：根据卖方和买方的代币ID设置价格数量。
  - `addNewPriceKey(byte[] sellTokenId, byte[] buyTokenId, MarketPairPriceToOrderStore pairPriceToOrderStore)`：如果代币对不存在，则添加新的代币对并设置数量为1，否则增加数量。

- **关键变量**：
  - 数据库名称（dbName）：用于初始化父类`TronStoreWithRevoking`的数据库名称。

- **相互依赖**：与`MarketPairPriceToOrderStore`模块交互，用于添加新的价格键时更新相关存储。

- **核心与辅助操作**：核心操作包括价格数量的获取和设置，辅助操作包括新价格键的添加和初始化。

- **操作序列**：首先检查代币对是否存在，如果不存在则添加新的代币对并设置数量为1，如果存在则增加数量。

- **性能方面**：性能考虑可能包括数据的快速检索和更新，以及确保并发操作的数据一致性。

- **可重用性**：该模块设计为可重用，可以在需要存储和检索特定代币对价格信息的任何地方使用。

- **使用**：主要用于加密货币交易平台中，管理和跟踪不同代币对的价格信息。

- **假设**：假设每个代币对的价格信息可以通过卖方ID和买方ID唯一确定，并且存储结构能够有效处理并发访问和更新。
## Flow Diagram [via mermaid]
```mermaid
flowchart LR
    subgraph ext[ ]
        direction TB
        start([Start]) -->|getPriceNum| getPriceNumOp[Get Price Number]
        start -->|setPriceNum| setPriceNumOp[Set Price Number]
        start -->|addNewPriceKey| addNewPriceKeyOp[Add New Price Key]
    end

    subgraph db[Database]
        direction TB
        priceStore[(Price Store)]
        orderStore[(Order Store)]
    end

    getPriceNumOp -->|read| priceStore
    setPriceNumOp -->|update| priceStore
    addNewPriceKeyOp -->|read / update| priceStore
    addNewPriceKeyOp -->|create / update| orderStore

    classDef process fill:#f9f,stroke:#333,stroke-width:2px;
    classDef datastore fill:#fcf,stroke:#f66,stroke-width:2px;
    classDef startend fill:#fff,stroke:#333,stroke-width:2px;
    classDef ext fill:#bbf,stroke:#fff,stroke-width:0px;

    class start,end startend;
    class getPriceNumOp,setPriceNumOp,addNewPriceKeyOp process;
    class priceStore,orderStore datastore;
```
