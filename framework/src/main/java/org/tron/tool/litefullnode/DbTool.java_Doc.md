## Module: DbTool.java
模块名：DbTool.java

主要目标：这个模块的主要目的是管理数据库对象，根据指定的路径获取数据库对象，创建数据库对象或从dbMap中获取已存在的对象，并关闭数据库。

关键函数：getDB()，closeDB()，close()，getDbType()，openLevelDb()，openRocksDb()，newDefaultRocksDbOptions()，getLevelDbOptions()

关键变量：KEY_ENGINE，ENGINE_FILE，FILE_SEPARATOR，ROCKSDB，dbMap，DbType，sourceDir，dbName，path，database，options

相互依赖：该模块与LevelDBImpl和RocksDBImpl模块有相互依赖关系，通过调用这两个模块的方法来打开LevelDB和RocksDB数据库。

核心与辅助操作：核心操作包括获取数据库对象、关闭数据库和关闭所有数据库；辅助操作包括获取数据库类型、打开LevelDB和RocksDB等。

操作序列：1. 根据指定路径获取数据库对象；2. 根据数据库类型打开LevelDB或RocksDB；3. 关闭数据库。

性能方面：该模块考虑了数据库的性能，如设置缓存大小、压缩类型、写缓冲区大小等参数来提高数据库性能。

可重用性：该模块具有良好的可重用性，可以在不同项目中方便地使用和扩展。

用法：该模块用于管理数据库对象，可以根据指定的路径和数据库名称获取数据库对象，并关闭数据库。

假设：该模块假设数据库文件和引擎属性文件已存在，并根据引擎属性文件中的配置确定数据库类型。
## Flow Diagram [via mermaid]
```mermaid
flowchart
    st=>start: Start
    e=>end: End
    op1=>operation: Get the DB object
    op2=>operation: Close DB
    op3=>operation: Close all DBs
    op4=>operation: Get DB type
    op5=>operation: Open LevelDB
    op6=>operation: Open RocksDB
    st->op1->op2->op3->op4
    op4->op5
    op4->op6
    op5->e
    op6->e
```
