## Module: SnapshotManager.java
模块名称：SnapshotManager.java

主要目的：此模块的主要目的是管理快照的创建、恢复和维护，以支持数据的版本控制和回滚机制。

关键功能：
- `init()`：初始化快照管理，设置检查点版本和定时清理任务。
- `buildSession(boolean forceEnable)`：创建一个会话，用于数据的修改和回滚。
- `commit()`、`revoke()`、`merge()`、`pop()`、`fastPop()`：这些方法分别用于提交更改、撤销更改、合并快照、弹出快照和快速弹出快照。
- `flush()`：刷新当前状态，创建检查点并清理旧的检查点。
- `shutdown()`：关闭快照管理器，清理资源。
- `check()`、`recover(TronDatabase<byte[]> tronDatabase)`：检查和恢复快照，确保数据的一致性。

关键变量：
- `dbs`：存储链基数据库的列表。
- `size`、`maxSize`：当前快照大小和最大快照大小。
- `flushCount`、`maxFlushCount`：当前刷新计数和最大刷新计数。
- `activeSession`：活跃的会话数。

依赖关系：与`Chainbase`、`ISession`、`TronDatabase`等类相互作用，这些类提供数据库操作、会话管理和数据存储的功能。

核心与辅助操作：核心操作包括会话管理（创建、提交、撤销）、快照合并和刷新。辅助操作包括初始化、资源清理和状态检查。

操作顺序：通常，操作顺序开始于`init()`方法的调用，随后根据需要创建会话进行数据操作，最后通过`commit()`、`revoke()`等方法来管理数据的状态。

性能方面：性能考虑包括优化快照合并和刷新操作，以及定时清理旧检查点以释放资源。

可重用性：提供了灵活的会话管理和快照操作接口，可以根据需要在不同的场景下重用和扩展。

使用方式：通过创建`SnapshotManager`实例，然后调用其方法来进行数据的快照管理和版本控制。

假设：假设底层数据库支持快照操作，且系统环境满足运行要求（如足够的存储空间用于创建检查点）。

以上是基于提供的代码模块的分析，涵盖了模块的主要目的、关键功能、变量、依赖关系等方面。
## Flow Diagram [via mermaid]
```mermaid
graph TD
    A[Start] -->|init| B(SnapshotManager)
    B -->|buildSession| C(Session Management)
    B -->|setCursor| D(Cursor Setting)
    B -->|add(IRevokingDB db)| E(DB Addition)
    B -->|advance| F(Advance Head)
    B -->|retreat| G(Retreat Head)
    B -->|merge| H(Merge Snapshots)
    B -->|revoke| I(Revoke Changes)
    B -->|commit| J(Commit Changes)
    B -->|pop| K(Pop Snapshot)
    B -->|flush| L(Flush Changes)
    B -->|createCheckpoint| M(Create Checkpoint)
    B -->|deleteCheckpoint| N(Delete Checkpoint)
    B -->|pruneCheckpoint| O(Prune Checkpoints)
    B -->|recover(TronDatabase<byte[]> tronDatabase)| P(Recover from Checkpoint)
    B -->|shutdown| Q(Shutdown)
    B -->|check| R(Initial Check)
    R -->|isV2Open| S{Checkpoint Version 2}
    S -->|true| T[Use Checkpoint v2 Logic]
    S -->|false| U[Use Checkpoint v1 Logic]
```
