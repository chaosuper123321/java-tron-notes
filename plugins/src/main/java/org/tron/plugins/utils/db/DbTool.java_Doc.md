## Module: DbTool.java
模块名称：DbTool.java

主要目标：该模块的目的是根据指定路径获取数据库对象，如果不存在则创建数据库对象，否则从dbMap中获取。

关键功能：主要方法/函数及其作用包括：
- getDB(String sourceDir, String dbName): 根据指定路径获取数据库对象。
- getDB(String sourceDir, String destDir, String dbName): 根据指定路径获取数据库对象，保持引擎与源相同。
- getDB(String sourceDir, String dbName, DbType type): 根据指定路径和引擎获取数据库对象。
- getDB(Path sourceDir, String dbName): 根据指定路径获取数据库对象，不受dbMap管理。
- closeDB(String sourceDir, String dbName): 关闭数据库。
- close(): 关闭所有数据库。

关键变量：关键变量包括KEY_ENGINE, ENGINE_FILE, FILE_SEPARATOR, ROCKSDB, dbMap。

相互依赖性：与其他系统组件的交互主要是通过dbMap来管理数据库对象。

核心操作与辅助操作：核心操作是获取数据库对象和关闭数据库，辅助操作包括判断数据库类型和打开数据库。

操作序列：操作序列包括根据路径获取数据库对象、判断数据库类型、打开数据库、关闭数据库等步骤。

性能方面：性能方面需要考虑数据库的打开和关闭效率。

可重用性：该模块具有较高的可重用性，可以适应不同的数据库类型和路径。

用法：该模块用于管理数据库对象的获取和关闭操作。

假设：假设数据库引擎为LevelDB或RocksDB，数据库文件存在于指定路径中。
## Flow Diagram [via mermaid]
```mermaid
flowchart
    st=>start: Start
    op1=>operation: getDB(sourceDir, dbName)
    op2=>operation: getDbType(sourceDir, dbName)
    cond1=>condition: type = getDbType
    op3=>operation: getDB(sourceDir, dbName, type)
    op4=>operation: openLevelDb(path)
    op5=>operation: openRocksDb(path)
    op6=>operation: getDB(sourceDir, destDir, dbName)
    op7=>operation: getDB(destDir, dbName, type)
    op8=>operation: getDB(sourceDir, dbName, type)
    op9=>operation: openLevelDb(path)
    op10=>operation: openRocksDb(path)
    op11=>operation: getDB(sourceDir, dbName)
    op12=>operation: closeDB(sourceDir, dbName)
    op13=>operation: close
    e=>end: End
    
    st->op1->op2->cond1
    cond1(yes)->op3
    cond1(no)->op4
    op3->e
    op4->e
    op6->op7->e
    op8->e
    op9->e
    op10->e
    op11->e
    op12->e
    op13->e
```
