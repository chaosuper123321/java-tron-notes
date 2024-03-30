## Module: ServiceContainer.java
模块名称：ServiceContainer.java

主要目标：该模块的目的是管理服务的容器，用于初始化、启动和停止服务。

关键功能：主要方法/函数及其作用包括：
- add(Service service)：向容器中添加服务。
- init()：初始化所有服务。
- init(CommonParameter parameter)：根据通用参数初始化所有服务。
- start()：启动所有服务。
- stop()：停止所有服务。
- blockUntilShutdown()：阻塞直到所有服务停止。

关键变量：重要变量包括services（存储服务的集合）。

相互依赖：与其他系统组件的交互包括与Service类的交互。

核心与辅助操作：核心操作包括初始化、启动和停止服务，辅助操作包括添加服务和阻塞直到服务停止。

操作序列：操作序列包括初始化服务、启动服务、停止服务等步骤。

性能方面：性能考虑主要包括服务的启动和停止效率。

可重用性：该模块具有良好的重用性，可以适应不同的服务需求。

用法：该模块用于管理各种服务，包括初始化、启动和停止操作。

假设：假设用户已了解服务的具体实现和使用方式。
## Flow Diagram [via mermaid]
```mermaid
flowchart
    st=>start: Start
    e=>end: End
    op1=>operation: ServiceContainer
    op2=>operation: add(Service service)
    op3=>operation: init
    op4=>operation: init(CommonParameter parameter)
    op5=>operation: start
    op6=>operation: stop
    op7=>operation: blockUntilShutdown
    
    st->op1->op2->op3->op4->op5->op6->op7->e
```
