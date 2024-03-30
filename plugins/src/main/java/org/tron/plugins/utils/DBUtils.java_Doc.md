## Module: DBUtils.java
模块名：DBUtils.java

主要目标：该模块的主要目标是提供数据库操作的工具类。

关键功能：主要方法/函数及其作用包括：
- newLevelDb：创建一个新的LevelDB数据库。
- newDefaultLevelDbOptions：创建默认的LevelDB选项。
- newDefaultRocksDbOptions：创建默认的RocksDB选项。
- newRocksDb：创建一个新的RocksDB数据库。
- newRocksDbForBulkLoad：为批量加载创建一个新的RocksDB数据库。
- newRocksDbReadOnly：创建一个只读的RocksDB数据库。
- simpleDecode：对字节数组进行简单解码。
- getTransactionId：获取交易的ID。

关键变量：关键变量包括：
- SPLIT_BLOCK_NUM
- MARKET_PAIR_PRICE_TO_ORDER
- CHECKPOINT_DB_V2
- TMP
- NODE_TYPE_LIGHT_NODE
- KEY_ENGINE
- FILE_ENGINE
- LEVELDB
- ROCKSDB

相互依赖：该模块与其他系统组件的交互包括数据库的创建、读取和写入操作以及对比较器的设置。

核心与辅助操作：主要操作包括创建数据库、设置选项、创建比较器等核心操作，辅助操作包括解码、获取交易ID等。

操作序列：操作序列包括创建LevelDB/RocksDB数据库、设置选项、创建比较器、解码等一系列操作流程。

性能方面：性能考虑包括对数据库大小、缓存大小、并发数等参数的设置，以及对压缩类型、块大小等性能优化的考虑。

可重用性：该模块具有较高的可重用性，可以用于创建不同类型的数据库并进行相应操作。

用法：该模块用于创建LevelDB和RocksDB数据库，并提供了一系列数据库操作的工具方法。

假设：该模块的假设包括数据库文件命名规则、数据库操作的正确性等方面的假设。
## Flow Diagram [via mermaid]
```mermaid
flowchart
    st=>start: Start
    e=>end: End
    op1=>operation: DBUtils.java
    op2=>operation: newLevelDb
    op3=>operation: newDefaultLevelDbOptions
    op4=>operation: newDefaultRocksDbOptions
    op5=>operation: newRocksDb
    op6=>operation: newRocksDbForBulkLoad
    op7=>operation: newRocksDbReadOnly
    op8=>operation: simpleDecode
    op9=>operation: getTransactionId

    st->op1->op2
    op2->op3
    op1->op4->op5
    op1->op4->op6
    op1->op4->op7
    op1->op8
    op1->op9
    op3->e
    op4->e
    op5->e
    op6->e
    op7->e
    op8->e
    op9->e
```
