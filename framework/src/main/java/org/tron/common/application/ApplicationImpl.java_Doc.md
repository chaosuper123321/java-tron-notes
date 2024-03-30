## Module: ApplicationImpl.java
- **模块名称**: ApplicationImpl.java
- **主要目标**: 定义其目的为管理应用程序的启动和关闭过程，以及提供各种服务。
- **关键功能**: 
   1. setOptions(Args args): 设置选项，但当前未使用。
   2. init(CommonParameter parameter): 初始化应用程序，创建服务容器。
   3. addService(Service service): 添加服务到服务容器。
   4. initServices(CommonParameter parameter): 初始化所有服务。
   5. startup(): 启动应用程序，启动网络服务和共识服务等。
   6. shutdown(): 关闭应用程序，关闭所有服务。
   7. startServices(): 启动所有服务。
   8. blockUntilShutdown(): 阻塞直到所有服务关闭。
   9. shutdownServices(): 关闭所有服务。
   10. getDbManager(): 获取数据库管理器。
   11. getChainBaseManager(): 获取链基础管理器。
- **关键变量**: 
   - services: 服务容器
   - tronNetService: Tron网络服务
   - dbManager: 数据库管理器
   - chainBaseManager: 链基础管理器
   - consensusService: 共识服务
- **相互依赖性**: 该模块依赖于Args、ServiceContainer等其他系统组件。
- **核心 vs. 辅助操作**: 核心操作包括启动、关闭应用程序和服务，辅助操作包括设置选项和添加服务。
- **操作顺序**: 启动应用程序->启动网络服务和共识服务->初始化所有服务->启动所有服务->关闭应用程序。
- **性能方面**: 需要考虑启动、关闭服务的性能开销。
- **可重用性**: 可以通过修改参数、替换服务等方式进行适应性重用。
- **使用**: 用于管理应用程序的启动和关闭，提供各种服务。
- **假设**: 未提供明确的假设。
## Flow Diagram [via mermaid]
```mermaid
graph TD
A[ApplicationImpl.java] -->|uses| B[ServiceContainer]
A -->|uses| C[TronNetService]
A -->|uses| D[Manager]
A -->|uses| E[ChainBaseManager]
A -->|uses| F[ConsensusService]
B -->|uses| G[Service]
B -->|initServices| H[CommonParameter]
B -->init|I[CommonParameter]
B -->initServices|J[CommonParameter]
B -->startServices
B -->blockUntilShutdown
B -->stop
D -->|close| A
E -->|return| A
F -->|start| G
F -->|stop| A
G -->|start| A
```
