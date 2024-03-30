## Module: ExchangeStore.java
**模块名称**：ExchangeStore.java

**主要目标**：此模块的目的是为了管理和存储交易所的数据信息，包括但不限于交易所的创建、查询等功能。

**关键函数**：
- `get(byte[] key)`：根据给定的键（比如交易所的ID）获取一个交易所的信息。如果找不到，会抛出`ItemNotFoundException`异常。
- `getAllExchanges()`：获取所有交易所的信息，并按创建时间降序排序。

**关键变量**：
- `dbName`：数据库名称，用于初始化父类`TronStoreWithRevoking`。

**依赖关系**：
- 依赖于`TronStoreWithRevoking`类进行数据的基础存储操作。
- 使用`org.springframework`中的注解进行依赖注入和配置属性值。

**核心与辅助操作**：
- 核心操作包括通过键获取特定交易所的信息(`get`方法)以及获取所有交易所的列表(`getAllExchanges`方法)。
- 辅助操作可能包括与数据库的连接和初始化过程，这是通过继承`TronStoreWithRevoking`类实现的。

**操作序列**：
- 通常，操作开始于通过`get`方法查询单个交易所的数据，或者通过`getAllExchanges`方法获取所有交易所的数据列表。

**性能方面**：
- 性能考虑可能包括如何高效地从数据库中检索数据，以及如何优化数据的排序过程。

**可重用性**：
- 该模块通过提供通用的数据访问接口和利用Java泛型，具有较高的可重用性，可以适应不同类型的交易所数据存储需求。

**使用情况**：
- 在需要管理交易所数据（如查询、存储交易所信息）的场景中使用此模块。

**假设**：
- 假设数据库已经正确配置，并且可以正常访问。
- 假设所有交易所的数据结构是统一的，即`ExchangeCapsule`可以准确地表示所有交易所的信息。

这个分析提供了对`ExchangeStore.java`模块的全面理解，从其主要目的到具体的实现细节，以及它在系统中的作用和使用方式。
## Flow Diagram [via mermaid]
```mermaid
flowchart LR
    client[Client] -->|get(key)| ExchangeStore
    client -->|getAllExchanges| ExchangeStore
    ExchangeStore -->|get from DB| revokingDB[(Database)]
    ExchangeStore -->|return ExchangeCapsule| client
    ExchangeStore -->|return List<ExchangeCapsule>| client
```
