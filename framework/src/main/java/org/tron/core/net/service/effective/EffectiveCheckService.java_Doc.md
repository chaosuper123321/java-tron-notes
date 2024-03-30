## Module: EffectiveCheckService.java
模块: EffectiveCheckService.java

主要目标: 此模块的目的是检查有效节点并维护网络连接的有效性。

关键功能: 
1. init() - 初始化模块，启动定时任务以查找有效节点。
2. triggerNext() - 触发下一个有效服务任务。
3. close() - 关闭模块。
4. isIsolateLand() - 检查是否处于孤立状态。
5. findEffectiveNode() - 尝试查找可同步的节点。
6. resetCount() - 重置计数器。
7. onDisconnect() - 处理断开连接事件。

关键变量: 
1. isEffectiveCheck - 是否启用有效性检查。
2. nodesCache - 缓存节点信息。
3. cur - 当前节点的InetSocketAddress。
4. count - 计数器。
5. esName - 线程池名称。
6. MAX_HANDSHAKE_TIME - 最大握手时间。

相互依赖: 
与TronNetDelegate、TronNetService等组件有交互。

核心与辅助操作: 
主要操作包括查找有效节点和维护连接有效性，辅助操作包括初始化和关闭模块。

操作序列: 
1. 初始化模块。
2. 定期检查有效节点。
3. 查找可同步节点。
4. 处理连接事件。

性能方面: 
考虑到定时任务的执行效率和节点查找的速度。

可重用性: 
模块可以适应不同的网络环境和需求，具有一定的重用性。

用途: 
用于检查维护网络连接的有效性，确保节点同步正常运行。

假设: 
假设节点之间的连接是可靠的，且有效节点可以提供有效的数据同步。
## Flow Diagram [via mermaid]
```mermaid
flowchart
    st=>start: Start
    op=>operation: init
    op2=>operation: triggerNext
    op3=>operation: close
    op4=>operation: isIsolateLand
    op5=>operation: findEffectiveNode
    op6=>operation: resetCount
    op7=>operation: onDisconnect
    e=>end: End

    st->op->op2->op3->op4->op5->op6->op7->e
```
