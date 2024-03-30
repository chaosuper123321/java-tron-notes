## Module: DefaultConfig.java
模块：DefaultConfig.java

主要目标：此模块的目的是配置Tron区块链的默认设置。

关键功能：主要方法/函数及其作用包括：
1. revokingDatabase() - 创建用于撤销数据库的RevokingDatabase实例。
2. getRpcApiServiceOnSolidity() - 根据是否为Solidity节点返回适当的RpcApiServiceOnSolidity实例。
3. getHttpApiOnSolidityService() - 根据是否为Solidity节点返回适当的HttpApiOnSolidityService实例。
4. getRpcApiServiceOnPBFT() - 根据是否为Solidity节点返回适当的RpcApiServiceOnPBFT实例。
5. getHttpApiOnPBFTService() - 根据是否为Solidity节点返回适当的HttpApiOnPBFTService实例。
6. backupRocksDBAspect() - 创建用于备份RocksDB的BackupRocksDBAspect实例。

关键变量：重要变量包括appCtx，commonConfig。

相互依赖性：与其他系统组件的交互包括CommonConfig类的导入。

核心与辅助操作：主要操作包括创建数据库实例和服务实例，辅助操作包括条件化的备份。

操作序列：主要流程涉及加载RocksDB库、设置解析器安全模式、创建数据库和服务实例。

性能方面：性能考虑包括加载库和创建实例的效率。

可重用性：该模块具有一定的可重用性，可以根据需要创建不同的服务实例。

用法：此模块用于配置Tron区块链的默认设置，根据节点类型创建相应的服务实例。

假设：假设使用Args类的实例来确定节点类型。
## Flow Diagram [via mermaid]
```mermaid
flowchart
    st=>start: Start
    e=>end: End
    op1=>operation: DefaultConfig.java
    op2=>operation: CommonConfig.java
    op3=>operation: RocksDB.loadLibrary
    op4=>operation: ParserConfig.getGlobalInstance.setSafeMode(true)
    op5=>operation: ApplicationContext appCtx
    op6=>operation: CommonConfig commonConfig
    op7=>operation: Thread.setDefaultUncaughtExceptionHandler
    op8=>operation: RevokingDatabase revokingDatabase
    op9=>operation: SnapshotManager
    op10=>operation: RpcApiServiceOnSolidity getRpcApiServiceOnSolidity
    op11=>operation: HttpApiOnSolidityService getHttpApiOnSolidityService
    op12=>operation: RpcApiServiceOnPBFT getRpcApiServiceOnPBFT
    op13=>operation: HttpApiOnPBFTService getHttpApiOnPBFTService
    op14=>operation: BackupRocksDBAspect backupRocksDBAspect
    
    st->op1->op3->op4->op5->op6->op7->op8->op9->op10->op11->op12->op13->op14->e
    op1->op2
```
