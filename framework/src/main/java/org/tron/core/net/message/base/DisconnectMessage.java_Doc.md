## Module: DisconnectMessage.java
- **模块名称**: DisconnectMessage.java
- **主要目标**: 该模块的目的是创建断开连接消息。
- **关键功能**: 
   1. `DisconnectMessage(byte type, byte[] rawData)`: 从原始数据解析断开连接消息。
   2. `DisconnectMessage(byte[] data)`: 从数据解析断开连接消息。
   3. `DisconnectMessage(ReasonCode reasonCode)`: 创建具有特定原因代码的断开连接消息。
   4. `getReason()`: 获取断开连接消息中的原因代码。
   5. `toString()`: 返回包含原因代码的字符串表示。
- **关键变量**: 
   - `disconnectMessage`: 存储断开连接消息的Protocol.DisconnectMessage对象。
- **相互依赖**: 该模块依赖Protocol和MessageTypes等其他系统组件。
- **核心 vs. 辅助操作**: 核心操作包括创建和解析断开连接消息，辅助操作可能包括其他数据处理。
- **操作序列**: 模块首先解析原始数据或数据数组，然后创建或获取原因代码。
- **性能方面**: 性能考虑可能涉及数据解析和构建的效率。
- **可重用性**: 该模块可以适用于不同的原因代码和断开连接消息场景。
- **用法**: 用于创建、解析和获取断开连接消息的原因代码。
- **假设**: 假设数据格式符合Protocol.DisconnectMessage的定义。
## Flow Diagram [via mermaid]
```mermaid
flowchart
    st=>start: Start
    op1=>operation: DisconnectMessage.java
    op2=>operation: TronMessage
    op3=>operation: Protocol.DisconnectMessage
    op4=>operation: ReasonCode
    e=>end: End

    st->op1
    op1->op2
    op2->op3
    op3->op4
    op4->e
```
