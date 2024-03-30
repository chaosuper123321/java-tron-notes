## Module: TxOutputUtil.java
模块名称：TxOutputUtil.java

主要目标：该模块的目的是创建新的交易输出。

关键功能：主要方法/函数及其作用包括：
- newTxOutput(long value, String address)：创建新的交易输出，设置价值和地址。

关键变量：重要变量包括value（价值）和address（地址）。

相互依赖性：该模块与其他系统组件的交互包括使用ByteArray和TXOutput等类。

核心与辅助操作：主要操作是创建新的交易输出，辅助操作包括设置价值和地址。

操作序列：操作序列包括调用newTxOutput方法来创建新的交易输出。

性能方面：性能方面需要考虑方法调用的效率和内存占用情况。

可重用性：该模块具有高可重用性，可以在不同的交易场景中重复使用。

用法：该模块用于创建新的交易输出，需要传入价值和地址参数。

假设：假设用户已经了解如何使用protobuf和ByteArray类。
## Flow Diagram [via mermaid]
```mermaid
flowchart
    st=>start: Start
    op1=>operation: newTxOutput(long value, String address)
    cond=>condition: Valid Parameters?
    op2=>operation: TXOutput.newBuilder
    op3=>operation: .setValue(value)
    op4=>operation: .setPubKeyHash(ByteString.copyFrom(ByteArray.fromHexString(address)))
    op5=>operation: .build
    e=>end: End

    st->op1->cond
    cond(yes)->op2->op3->op4->op5->e
    cond(no)->e
```
