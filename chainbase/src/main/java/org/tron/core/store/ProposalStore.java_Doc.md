## Module: ProposalStore.java
- **模块名称**：ProposalStore.java

- **主要目标**：该模块的目的是提供一个存储和检索提案（Proposal）对象的机制，支持对提案数据的基本操作，如获取、存储和检索特定状态或条件的提案。

- **关键函数**：
  - `get(byte[] key)`：根据提供的键（提案ID）获取相应的提案对象。如果找不到，会抛出`ItemNotFoundException`异常。
  - `getAllProposals()`：获取所有提案对象，并按创建时间降序排序。
  - `getSpecifiedProposals(State state, long code)`：根据提案的状态和参数代码获取符合条件的提案列表，并按过期时间升序排序。

- **关键变量**：无直接声明的关键变量，但`dbName`（数据库名称），传递给父类`TronStoreWithRevoking`，是一个关键配置项，用于指定存储提案数据的数据库。

- **互依赖性**：该模块依赖于`TronStoreWithRevoking`类来实现对底层数据库的操作，并通过`@Autowired`注解自动注入所需的依赖项。此外，它还依赖于`ProposalCapsule`类来处理提案数据。

- **核心与辅助操作**：核心操作包括获取、存储和检索提案数据（如`get`和`getAllProposals`方法）。辅助操作可能包括对提案数据进行排序和过滤（如在`getAllProposals`和`getSpecifiedProposals`中实现）。

- **操作序列**：操作序列通常开始于调用`get`方法获取单个提案，或调用`getAllProposals`和`getSpecifiedProposals`方法获取提案列表，后者包含了排序和过滤逻辑。

- **性能方面**：性能考虑可能包括对数据库访问的优化，以及如何高效地进行排序和过滤操作。由于使用了Java 8的Stream API，这可能对处理大量提案时的性能有所影响。

- **可重用性**：该模块设计为特定于处理`ProposalCapsule`对象的存储和检索，但其模式和方法可以适应于类似的用例，特别是在需要对存储在Tron网络中的数据进行管理时。

- **使用**：该模块被设计为Tron区块链框架的一部分，用于管理网络提案。它通过提供方法来获取和检索提案数据，支持网络治理和提案决策过程。

- **假设**：该模块的实现假设提案数据将被存储在一个支持键值对访问的数据库中，并且这些数据可以通过提案ID进行索引和检索。此外，还假设提案对象可以通过`ProposalCapsule`类的实例进行序列化和反序列化。
## Flow Diagram [via mermaid]
```mermaid
flowchart TB
    subgraph ext [External Entities]
        users([Users/Systems])
    end

    subgraph ds [Data Store]
        db[(Proposal Database)]
    end

    subgraph pr [Process]
        getAllProposals[Get All Proposals]
        getSpecifiedProposals[Get Specified Proposals]
        getProposal[Get Proposal by Key]
    end

    users -->|Request Proposal(s)| getAllProposals
    users -->|Request Proposal(s) by State and Code| getSpecifiedProposals
    users -->|Request Proposal by Key| getProposal

    getAllProposals -->|Read from| db
    getSpecifiedProposals -->|Read & Filter from| db
    getProposal -->|Read from| db
```
