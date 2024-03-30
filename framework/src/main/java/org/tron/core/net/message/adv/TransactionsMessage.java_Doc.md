## Module: TransactionsMessage.java
- **模块名称**: TransactionsMessage.java
- **主要目标**: 该模块的目的是处理和管理交易信息。
- **关键功能**: 
   - `TransactionsMessage(List<Transaction> trxs)`: 接收交易列表并构建交易消息。
   - `TransactionsMessage(byte[] data)`: 解析字节数组数据并验证交易信息。
   - `getTransactions()`: 返回交易信息。
   - `toString()`: 返回包含交易数量的字符串表示。
- **关键变量**: 
   - `transactions`: 存储交易信息的对象。
- **相互依赖**: 与其他系统组件的交互主要是接收和发送交易信息。
- **核心 vs. 辅助操作**: 核心操作是构建和解析交易信息，辅助操作包括验证和返回交易数量。
- **操作序列**: 接收交易列表 -> 构建交易消息 -> 发送或接收交易信息 -> 验证交易信息。
- **性能方面**: 需要考虑数据处理效率和内存占用。
- **可重用性**: 可以适应不同的交易信息处理需求，方便重复使用。
- **用法**: 用于处理和传输交易信息，如验证合约和交易数量。
- **假设**: 假设传入的数据符合交易信息的格式要求。
## Flow Diagram [via mermaid]
```mermaid
flowchart
    st=>start: Start
    op1=>operation: TransactionsMessage.java
    op2=>operation: flowchartpackage org.tron.core.net.message.adv;
    op3=>operation: import java.util.List;
    op4=>operation: import org.tron.core.capsule.TransactionCapsule;
    op5=>operation: import org.tron.core.net.message.MessageTypes;
    op6=>operation: import org.tron.core.net.message.TronMessage;
    op7=>operation: import org.tron.protos.Protocol;
    op8=>operation: import org.tron.protos.Protocol.Transaction;
    op9=>operation: public class TransactionsMessage extends TronMessage {
    op10=>operation: private Protocol.Transactions transactions;
    op11=>operation: public TransactionsMessage(List<Transaction> trxs) {
    op12=>operation: Protocol.Transactions.Builder builder = Protocol.Transactions.newBuilder;
    op13=>operation: trxs.forEach(trx -> builder.addTransactions(trx));
    op14=>operation: this.transactions = builder.build;
    op15=>operation: this.type = MessageTypes.TRXS.asByte;
    op16=>operation: this.data = this.transactions.toByteArray;
    op17=>operation: }
    op18=>operation: public TransactionsMessage(byte[] data) throws Exception {
    op19=>operation: super(data);
    op20=>operation: this.type = MessageTypes.TRXS.asByte;
    op21=>operation: this.transactions = Protocol.Transactions.parseFrom(getCodedInputStream(data));
    op22=>operation: if (isFilter) {
    op23=>operation: compareBytes(data, transactions.toByteArray);
    op24=>operation: TransactionCapsule.validContractProto(transactions.getTransactionsList);
    op25=>operation: }
    op26=>operation: }
    op27=>operation: public Protocol.Transactions getTransactions {
    op28=>operation: return transactions;
    op29=>operation: }
    op30=>operation: @Override
    op31=>operation: public String toString {
    op32=>operation: return new StringBuilder.append(super.toString).append(trx size: ).append(this.transactions.getTransactionsList.size).toString;
    op33=>operation: }
    op34=>operation: @Override
    op35=>operation: public Class<?> getAnswerMessage {
    op36=>operation: return null;
    op37=>operation: }
    e=>end: End

    st->op1->op2->op3->op4->op5->op6->op7->op8->op9->op10->op11->op12->op13->op14->op15->op16->op17->op18->op19->op20->op21->op22->op23->op24->op25->op26->op27->op28->op29->op30->op31->op32->op33->op34->op35->op36->op37->e
```
