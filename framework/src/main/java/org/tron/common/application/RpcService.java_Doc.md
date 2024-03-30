## Module: RpcService.java
模块名称: RpcService.java

主要目标: 该模块的目的是实现RPC服务的功能。

关键功能: 
- blockUntilShutdown(): 阻塞直到服务器关闭。
- start(): 启动RPC服务器。
- stop(): 停止RPC服务器。
- equals(Object o): 比较两个RpcService对象是否相等。
- hashCode(): 计算RpcService对象的哈希值。

关键变量: 
- apiServer: 用于处理RPC请求的服务器。
- port: 服务器端口号。

相互依赖: 该模块与其他系统组件的交互主要在启动、停止RPC服务器以及处理RPC请求时发生。

核心操作与辅助操作: 核心操作包括启动、停止服务器等，辅助操作包括比较对象相等性和计算哈希值。

操作序列: 
1. 调用start()方法启动RPC服务器。
2. 调用blockUntilShutdown()方法阻塞直到服务器关闭。
3. 调用stop()方法停止RPC服务器。

性能方面考虑: 在启动和停止RPC服务器时需要考虑性能因素，确保操作效率和稳定性。

可重用性: 该模块实现了通用的RPC服务功能，可以在不同项目中重复使用。

用法: 通过继承RpcService类，实现具体的RPC服务功能，并调用相应的方法启动、停止服务器等操作。

假设: 
- 假设使用者已了解RPC服务的基本概念和实现方式。
- 假设服务器端口号在初始化时已设置。
## Flow Diagram [via mermaid]
```mermaid
graph TD
A[RpcService.java] -->|implements| B[Service]
B --> C[blockUntilShutdown]
B --> D[start]
B --> E[stop]
B --> F[equals(Object o)]
B --> G[hashCode]
```
