## Module: TransactionMessage.java
模块名称：TransactionMessage.java

主要目标：该模块的目的是处理和传递交易信息。

关键功能：主要方法和功能包括：
1. TransactionMessage：构造函数，用于解析交易信息并设置消息类型。
2. toString：返回消息的字符串表示形式。
3. getMessageId：获取交易的消息ID。
4. getAnswerMessage：获取回复消息的类。
5. getTransactionCapsule：获取交易信息的胶囊对象。

关键变量：重要变量包括transactionCapsule、data、type。

相互依赖：该模块与其他系统组件的交互主要涉及交易信息的传递和处理。

核心操作 vs. 辅助操作：主要操作是解析交易信息和设置消息类型，辅助操作包括返回字符串表示形式和获取交易ID。

操作序列：模块的操作序列包括解析交易信息、设置消息类型、获取消息ID等步骤。

性能方面：性能考虑主要包括数据解析和处理的效率。

可重用性：该模块具有一定的可重用性，可以用于处理不同交易信息的传递。

用法：该模块用于创建和处理交易消息，可以在区块链系统中用于交易传输和验证。

假设：假设该模块的输入数据符合交易信息的格式要求。
## Flow Diagram [via mermaid]
```mermaid
flowchart
    st=>start: Start
    op1=>operation: TransactionMessage.java
    op2=>operation: TransactionMessage(byte[] data)
    op3=>operation: TransactionMessage(Transaction trx)
    op4=>operation: toString
    op5=>operation: getMessageId
    op6=>operation: getAnswerMessage
    op7=>operation: getTransactionCapsule
    e=>end: End
    
    st->op1
    op1->op2
    op1->op3
    op1->op4
    op1->op5
    op1->op6
    op1->op7
    op2->e
    op3->e
    op4->e
    op5->e
    op6->e
    op7->e
```
