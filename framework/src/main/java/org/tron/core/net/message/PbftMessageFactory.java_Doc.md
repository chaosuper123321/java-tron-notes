## Module: PbftMessageFactory.java
- **模块名称**: PbftMessageFactory.java
- **主要目标**: 创建PBFT消息对象。
- **关键功能**: 
   - create(byte[] data): 从字节数组创建PBFT消息对象。
   - create(byte type, byte[] packed): 根据消息类型创建相应的PBFT消息对象。
- **关键变量**: 
   - LEN: 存储长度信息。
   - TYPE: 存储消息类型信息。
- **相互依赖**: 与其他系统组件的交互有限，主要用于创建和解析PBFT消息。
- **核心 vs 辅助操作**: 核心操作为创建PBFT消息对象，辅助操作为异常处理和类型判断。
- **操作序列**: 接收数据 -> 解析消息类型 -> 创建相应的PBFT消息对象。
- **性能方面**: 性能考虑主要集中在数据解析和消息创建的效率。
- **可重用性**: 该模块可以轻松地适应不同的PBFT消息类型，具有一定的重用性。
- **用法**: 用于解析和创建PBFT消息对象，可用于网络通信中的消息处理。
- **假设**: 假设输入数据符合预期的消息格式，且异常情况会被适当处理。
## Flow Diagram [via mermaid]
```mermaid
flowchart
    st(PbftMessageFactory.java)
    op1(LEN = , len=)
    op2(TYPE = type=)
    op3(create(byte[] data) throws Exception)
    op4(create(byte type, byte[] packed) throws Exception)
    op5(MessageTypes receivedTypes = MessageTypes.fromByte(type))
    op6(return new PbftMessage(packed))
    op7(throw new P2pException(P2pException.TypeEnum.NO_SUCH_MESSAGE, TYPE + type + LEN + packed.length))
    op8(throw new P2pException(P2pException.TypeEnum.NO_SUCH_MESSAGE, receivedTypes.toString + LEN + packed.length))
    
    st --> op1
    st --> op2
    st --> op3
    op3 --> op4
    op4 --> op5
    op5 --> op6
    op5 --> op7
    op6 --> op3
    op7 --> op3
    op4 --> op8
```
