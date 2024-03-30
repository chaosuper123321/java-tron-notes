## Module: CommonDataBase.java
- **模块名称**: CommonDataBase.java

- **主要目标**: 该模块的目的是提供一个通用的数据库访问层，用于存储和检索字节数据。它是Tron区块链项目中用于处理各种数据库操作的核心组件之一。

- **关键功能**:
  - `put(byte[] key, byte[] item)`: 将键值对存储到数据库中。
  - `delete(byte[] key)`: 从数据库中删除指定键的数据。
  - `get(byte[] key)`: 根据键从数据库中检索数据。
  - `has(byte[] key)`: 检查数据库中是否存在指定的键。
  - `saveLatestPbftBlockNum(long number)`: 保存最新的PBFT区块编号。
  - `getLatestPbftBlockNum()`: 获取最新的PBFT区块编号。

- **关键变量**:
  - `LATEST_PBFT_BLOCK_NUM`: 用于标识最新PBFT区块编号的键。

- **依赖关系**: 该类继承自`TronDatabase<byte[]>`，依赖于`dbSource`（数据库源）进行数据的实际存储和检索操作。

- **核心与辅助操作**: 
  - 核心操作包括数据的增删改查（CRUD）功能，即`put`、`delete`、`get`、`has`方法。
  - 辅助操作包括处理特定数据（如PBFT区块编号）的`saveLatestPbftBlockNum`和`getLatestPbftBlockNum`方法。

- **操作序列**: 通常，使用此模块时，会首先调用`put`或`delete`方法修改数据库，然后通过`get`或`has`方法查询数据。`saveLatestPbftBlockNum`和`getLatestPbftBlockNum`方法用于特定场景下的数据处理。

- **性能方面**: 在设计和实现时需要考虑到数据存取的效率和数据库的响应时间，尤其是在处理大量数据时。

- **可重用性**: 由于该模块提供了通用的数据库访问接口，它可以在多个项目或模块中复用，特别是在需要处理字节数据的场景中。

- **使用**: 在Tron区块链项目中，该模块被用于存储和管理各种关键数据，包括但不限于区块信息、交易记录等。

- **假设**:
  - 假设`dbSource`已经被正确初始化，且可以正常访问底层数据库。
  - 假设存储的数据量在数据库的处理能力范围内。
## Flow Diagram [via mermaid]
```mermaid
flowchart LR
    ExternalEntity([External Entity]) -- key, item --> PutMethod([Put])
    ExternalEntity -- key --> GetMethod([Get])
    ExternalEntity -- key --> DeleteMethod([Delete])
    ExternalEntity -- key --> HasMethod([Has])
    ExternalEntity -- number --> SaveLatestPbftBlockNumMethod([SaveLatestPbftBlockNum])
    ExternalEntity --   --> GetLatestPbftBlockNumMethod([GetLatestPbftBlockNum])

    DataBase[(Database)] -- item --> PutMethod
    DataBase -- key --> GetMethod
    DataBase -- key --> DeleteMethod
    DataBase -- key --> HasMethod
    DataBase -- number --> SaveLatestPbftBlockNumMethod
    DataBase --   --> GetLatestPbftBlockNumMethod

    PutMethod -.-> DataBase
    GetMethod --> ExternalEntity
    DeleteMethod -.-> DataBase
    HasMethod --> ExternalEntity
    SaveLatestPbftBlockNumMethod -.-> DataBase
    GetLatestPbftBlockNumMethod --> ExternalEntity

    classDef process fill:#f9f,stroke:#333,stroke-width:2px;
    classDef datastore fill:#fcf,stroke:#f66,stroke-width:2px;
    classDef external fill:#bbf,stroke:#33a,stroke-width:2px;

    class PutMethod,GetMethod,DeleteMethod,HasMethod,SaveLatestPbftBlockNumMethod,GetLatestPbftBlockNumMethod process;
    class DataBase datastore;
    class ExternalEntity external;
```
