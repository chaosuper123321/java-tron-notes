## Module: FileUtils.java
模块：FileUtils.java

主要目标：该模块的主要目标是提供文件操作的相关功能，包括读取和写入属性、删除目录、创建文件和目录、复制文件等。

关键功能：主要方法/函数及其作用包括：
- isLevelDBEngine：检查指定路径下是否使用LevelDB引擎。
- readProperty：读取属性文件中指定键的值。
- writeProperty：写入属性文件指定键值对。
- deleteDir：递归删除目录及其子目录。
- createFileIfNotExists：如果文件不存在则创建文件。
- createDirIfNotExists：如果目录不存在则创建目录。
- isExists：检查文件或目录是否存在。
- isSymbolicLink：检查文件是否为符号链接。
- copyDatabases：复制数据库文件及子目录。
- copyDir：复制目录及其子目录。
- copy：复制文件或创建硬链接。

关键变量：关键变量包括文件路径、属性键值、文件内容等。

相互依赖：该模块与其他系统组件的交互主要体现在文件操作和属性读写方面。

核心vs. 辅助操作：核心操作包括文件复制、删除、创建等；辅助操作包括属性读写、符号链接检查等。

操作序列：操作序列包括检查引擎类型、读写属性、删除目录、创建文件和目录、复制文件等一系列操作。

性能方面：在复制大量文件时，性能可能受到影响，需要考虑优化方案。

可重用性：该模块提供了多个通用的文件操作方法，可在不同场景下重复使用。

用法：该模块可用于处理文件操作，如读取、写入、复制、删除等操作。

假设：假设文件路径、属性键值等参数是有效的，操作过程中可能会出现异常情况。
## Flow Diagram [via mermaid]
```mermaid
flowchart
    st=>start: Start
    e=>end: End
    op1=>operation: isLevelDBEngine
    op2=>operation: readProperty
    op3=>operation: writeProperty
    op4=>operation: deleteDir
    op5=>operation: createFileIfNotExists
    op6=>operation: createDirIfNotExists
    op7=>operation: isExists
    op8=>operation: isSymbolicLink
    op9=>operation: copyDatabases
    op10=>operation: copyDir
    op11=>operation: copy

    st->op1
    op1->op2
    op2->op3
    op3->op4
    op4->op5
    op5->op6
    op6->op7
    op7->op8
    op8->op9
    op9->op10
    op10->op11
    op11->e
```
