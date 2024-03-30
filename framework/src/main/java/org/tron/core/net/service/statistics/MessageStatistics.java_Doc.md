## Module: MessageStatistics.java
模块名: MessageStatistics.java

主要目标: 该模块的主要目的是统计和记录不同类型消息的数量。

关键功能: 
- addTcpInMessage(Message msg): 将接收到的消息进行统计。
- addTcpOutMessage(Message msg): 将发送出去的消息进行统计。
- addTcpMessage(Message msg, boolean flag): 根据消息类型和标志位进行消息计数。
- messageProcess(): 处理不同类型消息的计数和统计。

关键变量: 
- p2pInHello, p2pOutHello, p2pInPing, p2pOutPing, p2pInPong, p2pOutPong等MessageCount变量用于记录不同类型消息的数量。

相互依赖: 该模块与其他系统组件的交互主要体现在消息类型的识别和计数上。

核心操作与辅助操作: 核心操作是消息计数和统计，辅助操作包括消息处理和类型识别。

操作顺序: 
1. 接收消息并进行计数。
2. 根据消息类型进行分类计数。
3. 处理不同类型消息的数量和相关信息。

性能方面: 该模块需要高效地处理大量消息，因此需要考虑性能优化和资源管理。

可重用性: 该模块具有较高的可重用性，可以用于不同系统中对消息进行统计和记录。

用法: 该模块通常用于网络服务中对消息传输情况进行监控和分析。

假设: 
- 假设消息类型是预先定义好的。
- 假设消息处理是同步进行的，不涉及异步操作。
## Flow Diagram [via mermaid]
```mermaid
flowchart
    st=>start: Start
    op=>operation: MessageStatistics.java
    sub1=>subroutine: MessageCount
    sub2=>subroutine: addTcpInMessage
    sub3=>subroutine: addTcpOutMessage
    sub4=>subroutine: addTcpMessage
    sub5=>subroutine: messageProcess
    
    st->op->sub1
    op->sub2
    op->sub3
    op->sub4
    op->sub5
```
