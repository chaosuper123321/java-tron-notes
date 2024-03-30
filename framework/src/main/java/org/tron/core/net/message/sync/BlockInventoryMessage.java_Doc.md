## Module: BlockInventoryMessage.java
模块名称：BlockInventoryMessage.java

主要目标：该模块的目的是处理区块库存消息。

关键功能：主要方法/功能及其作用包括：
1. BlockInventoryMessage(byte[] data)：构造函数，从数据中解析区块库存消息。
2. BlockInventoryMessage(List<BlockId> blockIds, BlockInventory.Type type)：构造函数，创建包含区块ID列表和类型的区块库存消息。
3. getBlockIds()：获取区块ID列表。

关键变量：重要变量包括blockInventory和blockIds。

相互依赖：与其他系统组件的交互主要是通过Protocol和BlockCapsule模块。

核心与辅助操作：主要操作是构造区块库存消息和获取区块ID列表，辅助操作包括解析区块消息和返回回答消息。

操作顺序：构造函数先解析数据并设置消息类型，然后构建区块库存消息。

性能方面：性能考虑主要涉及数据解析和消息构建的效率。

可重用性：该模块具有良好的可重用性，可以根据需要创建不同类型的区块库存消息。

用法：该模块用于处理区块库存消息的创建和解析。

假设：假设输入的数据格式符合区块库存消息的要求。
## Flow Diagram [via mermaid]
```mermaid
flowchart
    st=>start: Start
    op1=>operation: BlockInventoryMessage.java
    op2=>operation: BlockInventoryMessage(byte[] data)
    op3=>operation: BlockInventoryMessage(List<BlockId> blockIds, BlockInventory.Type type)
    op4=>operation: getAnswerMessage
    op5=>operation: getBlockInventory
    op6=>operation: getBlockIds
    e=>end: End

    st->op1
    op1->op2
    op1->op3
    op1->op4
    op1->op5
    op1->op6
```
