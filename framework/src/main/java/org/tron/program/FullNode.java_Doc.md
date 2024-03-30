## Module: FullNode.java
模块名: FullNode.java

主要目标: 该模块的主要目标是启动Full Node，提供TRON区块链网络的服务。

关键功能: 
- load(String path): 加载日志配置文件。
- main(String[] args): 启动Full Node，初始化各种服务并监听请求。

关键变量: 
- file: 用于表示文件对象。
- lc: LoggerContext对象，用于日志记录。
- configurator: JoranConfigurator对象，用于配置日志。
- parameter: CommonParameter对象，用于存储常用参数。

相互依赖: 该模块依赖于各种服务和配置文件，包括日志配置文件、参数配置等。

核心操作 vs. 辅助操作: 核心操作包括启动Full Node、初始化服务等；辅助操作包括加载日志配置、打印帮助信息等。

操作序列: 
1. 加载日志配置文件。
2. 初始化Metrics。
3. 创建Application实例。
4. 注册各种服务。
5. 启动Full Node服务。
6. 阻塞直到关闭。

性能方面: 该模块需要考虑日志记录、服务启动等操作对性能的影响。

可重用性: 该模块可以根据需要进行配置和扩展，适用于不同的Full Node实例。

用法: 该模块用于启动Full Node服务，并提供各种API和服务。

假设: 该模块假设配置文件和参数文件已经准备就绪，且能够正常加载和解析。
## Flow Diagram [via mermaid]
```mermaid
flowchart
    st=>start: Start
    op1=>operation: load(path)
    op2=>operation: main(args)
    op3=>operation: Metrics.init
    op4=>operation: DefaultListableBeanFactory
    op5=>operation: TronApplicationContext
    op6=>operation: ApplicationFactory.create(context)
    op7=>operation: RpcApiService
    op8=>operation: FullNodeHttpApiService
    op9=>operation: FullNodeJsonRpcHttpService
    op10=>operation: RpcApiServiceOnSolidity
    op11=>operation: HttpApiOnSolidityService
    op12=>operation: JsonRpcServiceOnSolidity
    op13=>operation: RpcApiServiceOnPBFT
    op14=>operation: HttpApiOnPBFTService
    op15=>operation: JsonRpcServiceOnPBFT
    e=>end: End

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
    op11->op12
    op12->op13
    op13->op14
    op14->op15
    op15->e
```
