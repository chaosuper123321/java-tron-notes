## Module: ExpandedSpendingKey.java
模块名称：ExpandedSpendingKey.java

主要目标：该模块的目的是定义和操作扩展的消费密钥。

关键功能：主要方法/函数及其作用包括：
1. getAkFromAsk(byte[] ask)：从ask获取ak。
2. getNkFromNsk(byte[] nsk)：从nsk获取nk。
3. decode(byte[] m_bytes)：解码方法，用于从字节数组中获取ask、nsk和ovk。
4. fullViewingKey()：生成完整的查看密钥。
5. encode()：将ask、nsk和ovk编码为字节数组。

关键变量：重要变量包括ask（消费授权密钥）、nsk（证明授权密钥）和ovk（传出查看密钥）。

相互依赖性：该模块与其他系统组件的交互主要在于使用JLibrustzcash库进行密钥转换。

核心与辅助操作：核心操作包括获取ak、nk，生成完整查看密钥等；辅助操作包括编码和解码方法。

操作序列：操作序列包括解码、生成完整查看密钥和编码。

性能方面：性能考虑主要在于JLibrustzcash库的效率和密钥转换的速度。

可重用性：该模块具有较高的可重用性，可以在不同的场景中重复使用。

用法：该模块用于处理扩展的消费密钥，包括生成完整查看密钥和编码解码操作。

假设：假设该模块需要使用JLibrustzcash库进行密钥转换，且输入的字节数组长度为96。
## Flow Diagram [via mermaid]
```mermaid
flowchart
    ExpandedSpendingKey.java
```
