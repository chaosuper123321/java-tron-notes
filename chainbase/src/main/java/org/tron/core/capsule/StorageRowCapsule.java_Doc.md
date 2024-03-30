## Module: StorageRowCapsule.java
模块名称：StorageRowCapsule.java

**主要目标**：此模块的主要目的是提供一个封装，用于表示和处理存储中的行数据。它允许对行键和行值的存取，以及标记行数据是否已被修改（脏数据）。

**关键功能**：
- 构造函数：提供了多个构造函数，允许从现有的StorageRowCapsule实例、键值对或仅值来创建实例。
- `markDirty()`：标记当前行数据为已修改。
- `getHash()`：计算并返回行值的Sha256哈希值。
- `getValue()` / `setValue(byte[] value)`：获取或设置行值。
- `getData()` / `getInstance()`：返回行值的字节数组。

**关键变量**：
- `byte[] rowValue`：存储行的值。
- `byte[] rowKey`：存储行的键。
- `boolean dirty`：标记行数据是否已被修改。

**相互依赖性**：此模块依赖于`org.tron.common.utils.Sha256Hash`来生成哈希值，还依赖于`org.tron.common.parameter.CommonParameter`进行配置参数的获取。

**核心与辅助操作**：
- 核心操作包括行数据的获取、设置和状态（脏或未脏）的管理。
- 辅助操作包括计算哈希值和数据的字符串表示。

**操作序列**：通常，实例化后会通过设置值或从现有实例克隆来使用此类。在数据被修改后，会标记为脏，以便可以进行适当的更新处理。

**性能方面**：性能考虑主要涉及到哈希计算和数据的克隆。使用有效的哈希函数和避免不必要的数据克隆可以帮助提高性能。

**可重用性**：此类设计为可重用，可以在需要处理存储行数据的不同部分和场景中使用。

**使用**：在java-tron项目中，此类用于处理区块链存储结构中的行数据，如账户状态、智能合约数据等。

**假设**：在设计此类时，假设行键和行值总是以字节数组的形式存在，且行数据的哈希值是通过Sha256算法计算得到的。
## Flow Diagram [via mermaid]
```mermaid
flowchart LR
    User((User)) -- Set Row Key/Value -->|rowKey, rowValue| StorageRowCapsule[Storage Row Capsule]
    User -- Get Row Value --> StorageRowCapsule
    StorageRowCapsule -- rowValue --> HashFunction[Hash Function]
    HashFunction -- Sha256 Hash --> User
    StorageRowCapsule -.-> |Mark as Dirty| DirtyFlag((Dirty Flag))

    classDef process fill:#f9f,stroke:#333,stroke-width:2px;
    classDef datastore fill:#fcf,stroke:#f66,stroke-width:2px,stroke-dasharray: 5, 5;
    classDef external fill:#bbf,stroke:#33f,stroke-width:2px;
    classDef flow fill:#fff,stroke:#333,stroke-width:2px,arrowhead:vee,arrowtail:inv;

    class StorageRowCapsule process;
    class HashFunction,DirtyFlag datastore;
    class User external;
```
