## Module: InventoryMessage.java
模块名：InventoryMessage.java

主要目标：该模块的目的是处理和传递库存信息。

关键功能：主要方法和功能包括：
1. InventoryMessage(byte[] data): 从字节数组数据中解析库存信息。
2. InventoryMessage(Inventory inv): 从给定的库存信息对象创建InventoryMessage。
3. InventoryMessage(List<Sha256Hash> hashList, InventoryType type): 根据哈希列表和库存类型创建InventoryMessage。
4. getInventory(): 获取库存信息对象。
5. getInvMessageType(): 获取库存消息类型。
6. getInventoryType(): 获取库存类型。
7. toString(): 将InventoryMessage对象转换为字符串表示。
8. getHashList(): 获取哈希列表。

关键变量：重要变量包括inv（Inventory类型的库存信息对象）。

相互依赖性：与其他系统组件的交互主要是与Protocol和MessageTypes相关。

核心与辅助操作：核心操作包括处理库存信息和消息类型的获取，辅助操作包括字符串表示和哈希列表处理。

操作序列：操作序列包括解析数据、创建库存信息对象、获取消息类型等步骤。

性能方面：性能考虑主要集中在数据解析和对象创建的效率。

可重用性：该模块具有良好的可重用性，可以根据不同的库存信息和类型进行定制。

用途：用于处理和传递不同类型的库存信息，如区块和交易。

假设：假设数据输入符合预期的格式和类型。
## Flow Diagram [via mermaid]
```mermaid
flowchart
    subgraph InventoryMessage.java
        class InventoryMessage {
            - inv: Inventory
            + InventoryMessage(byte[] data)
            + InventoryMessage(Inventory inv)
            + InventoryMessage(List<Sha256Hash> hashList, InventoryType type)
            + getAnswerMessage: Class<?>
            + getInventory: Inventory
            + getInvMessageType: MessageTypes
            + getInventoryType: InventoryType
            + toString: String
            + getHashList: List<Sha256Hash>
        }
    end
flowchart
    class TronMessage {
        - data: byte[]
        - type: byte
        + TronMessage(byte[] data)
    }
flowchart
    class Protocol.Inventory {
        + parseFrom(data: byte[]): Inventory
    }
flowchart
    class Sha256Hash {
        + getByteString: ByteString
        + wrap(data: byte[]): Sha256Hash
    }
flowchart
    class MessageTypes {
        + INVENTORY: byte
        + BLOCK: byte
        + TRX: byte
    }
flowchart
    class Deque {
    }
flowchart
    class LinkedList {
    }
flowchart
    class List {
        + stream: Stream
        + collect(Collectors.toList): List
    }
flowchart
    class StringBuilder {
        + append(str: String): StringBuilder
    }
flowchart
    class ByteString {
    }
flowchart
    class Stream {
        + map: Stream
    }
```
