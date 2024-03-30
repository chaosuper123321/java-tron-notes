## Module: HttpService.java
- **模块名称**: HttpService.java
- **主要目标**: 该模块的目的是提供HTTP服务的功能。
- **关键功能**: 
   - blockUntilShutdown(): 阻塞直到服务器关闭。
   - start(): 启动HTTP服务器。
   - stop(): 停止HTTP服务器。
   - equals(Object o): 比较端口号是否相同。
   - hashCode(): 返回类名和端口号的哈希值。
- **关键变量**: 
   - apiServer: 用于存储HTTP服务器实例。
   - port: 存储端口号。
- **相互依赖**: 与其他系统组件的交互包括启动、停止和阻塞操作。
- **核心 vs. 辅助操作**: 核心操作包括启动、停止服务器，辅助操作为比较端口号和计算哈希值。
- **操作顺序**: 首先启动服务器，然后阻塞或停止服务器。
- **性能方面**: 考虑到启动和停止服务器的性能。
- **可重用性**: 可以根据需要重用该模块提供的HTTP服务功能。
- **用法**: 该模块可用于启动、停止和阻塞HTTP服务器。
- **假设**: 假设使用者已经了解HTTP服务的基本概念和操作。
## Flow Diagram [via mermaid]
```mermaid
flowchart
    st=>start: Start
    op1=>operation: HttpService.java
    op2=>operation: Service
    op3=>operation: blockUntilShutdown
    op4=>operation: start
    op5=>operation: stop
    op6=>operation: equals(Object o)
    op7=>operation: hashCode
    e=>end: End

    st->op1
    op1->op2
    op2->op3
    op3->op4
    op4->op5
    op5->op6
    op6->op7
    op7->e
```
