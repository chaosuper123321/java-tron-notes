## Module: DelegatedResourceStore.java
- **模块名称**：DelegatedResourceStore.java

- **主要目标**：该模块的主要目的是管理和存储委托资源数据，包括能量和带宽的委托和解锁操作。

- **关键功能**：
  - `get(byte[] key)`：根据键值获取委托资源。
  - `getByFrom(byte[] key)`：根据来源地址获取所有委托资源（已标记为废弃）。
  - `unLockExpireResource(byte[] from, byte[] to, long now)`：解锁过期的委托资源，更新能量和带宽的冻结余额。

- **关键变量**：
  - `dbName`：数据库名称，用于初始化存储。
  - `revokingDB`：撤销数据库实例，用于执行底层存储操作。

- **依赖关系**：该模块依赖于`TronStoreWithRevoking`类来实现撤销功能，并使用`DelegatedResourceCapsule`封装委托资源的数据结构。

- **核心与辅助操作**：
  - 核心操作包括委托资源的获取、更新和解锁。
  - 辅助操作可能包括与数据库交互的底层逻辑，如检索和存储数据。

- **操作序列**：一个典型的操作序列可能是首先检查资源是否过期，然后根据过期状态更新资源的冻结余额，最后将更新后的资源存储回数据库。

- **性能方面**：性能考虑可能包括优化数据库访问以减少延迟，以及确保数据结构的高效管理以支持快速检索和更新。

- **可重用性**：该模块的设计允许它被重用于不同的上下文中，特别是在需要委托资源管理功能的任何区块链或分布式账本技术中。

- **使用**：该模块用于Tron区块链系统中，管理用户之间委托的能量和带宽资源，支持资源的有效分配和利用。

- **假设**：
  - 假设数据库操作是可靠的，并且能够处理并发访问。
  - 假设用户正确地委托和解锁资源，遵循智能合约或系统规则的要求。

这个分析概述了`DelegatedResourceStore.java`模块的主要特点和功能，提供了对其设计和实现的深入理解。
## Flow Diagram [via mermaid]
```mermaid
flowchart TB
    subgraph DelegatedResourceStore
    get[(get(key))]
    getByFrom[(getByFrom(key))]
    unLockExpireResource[(unLockExpireResource(from, to, now))]
    put[(put(key, value))]
    delete[(delete(key))]
    end

    database[(revokingDB)]
    DelegatedResourceCapsule((DelegatedResourceCapsule))

    get --> database
    getByFrom --> database
    unLockExpireResource --> database
    put --> database
    delete --> database

    database --> DelegatedResourceCapsule
    DelegatedResourceCapsule --> unLockExpireResource
```
