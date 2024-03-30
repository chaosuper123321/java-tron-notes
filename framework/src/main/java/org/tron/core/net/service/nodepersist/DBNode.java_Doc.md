## Module: DBNode.java
- **模块名称**: DBNode.java
- **主要目标**: 该模块的目的是存储和管理数据库节点的信息。
- **关键功能**: 
   1. `host`：获取和设置数据库节点的主机地址。
   2. `port`：获取和设置数据库节点的端口号。
   3. 构造函数：用于初始化数据库节点对象。
- **关键变量**: 
   1. `host`：存储数据库节点的主机地址。
   2. `port`：存储数据库节点的端口号。
- **相互依赖性**: 该模块与其他系统组件的交互主要涉及到数据库节点信息的存储和访问。
- **核心 vs. 辅助操作**: 核心操作包括获取和设置数据库节点的主机地址和端口号，辅助操作可能包括其他与数据库节点相关的功能。
- **操作序列**: 模块的操作序列包括初始化数据库节点对象、设置主机地址和端口号等步骤。
- **性能方面**: 由于该模块主要用于存储节点信息，性能方面主要取决于对节点信息的快速访问和更新。
- **可重用性**: 该模块具有较高的可重用性，可以在不同的系统中用于管理数据库节点信息。
- **用法**: 该模块通常用于存储和管理数据库节点的信息，可以在网络服务中用于维护节点列表。
- **假设**: 假设该模块主要用于简单的数据库节点信息存储和访问，不涉及复杂的数据库操作。
## Flow Diagram [via mermaid]
```mermaid
flowchart
    st=>start: Start
    op1=>operation: DBNode.java
    sub1=>subroutine: package org.tron.core.net.service.nodepersist;
    sub2=>subroutine: import lombok.Getter;
    sub3=>subroutine: import lombok.Setter;
    op2=>operation: public class DBNode {
    op3=>operation: @Getter
    op4=>operation: @Setter
    op5=>operation: private String host;
    op6=>operation: private int port;
    op7=>operation: public DBNode {
    op8=>operation: public DBNode(String host, int port) {
    op9=>operation: this.host = host;
    op10=>operation: this.port = port;
    e=>end: End

    st->op1->sub1->sub2->sub3->op2->op3->op4->op5->op6->op7->op8->op9->op10->e
```
