## Module: Sha256Hash.java
模块名称：Sha256Hash.java

主要目标：该模块的主要目标是提供对SHA-256哈希功能的封装，以便能够正确地进行equals和hashcode操作，从而可以在映射中使用。

关键功能：主要方法/函数及其作用包括：
1. Sha256Hash(byte[] rawHashBytes)：构造函数，用于创建Sha256Hash实例，并检查长度是否正确。
2. static Sha256Hash wrap(byte[] rawHashBytes)：静态方法，用于创建包装给定哈希值的新实例。
3. static Sha256Hash of(boolean isSha256, byte[] contents)：静态方法，用于创建包含给定字节的计算（一次性）哈希值的新实例。
4. static Sha256Hash of(boolean isSha256, File file)：静态方法，用于创建包含给定文件内容的计算（一次性）哈希值的新实例。
5. static MessageDigest newDigest()：静态方法，返回一个新的SHA-256 MessageDigest实例。
6. static SM3Digest newSM3Digest()：静态方法，返回一个新的SM3 MessageDigest实例。
7. static byte[] hash(boolean isSha256, byte[] input)：静态方法，计算给定字节的SHA-256哈希值。
8. equals(Object o)：重写equals方法，用于比较两个Sha256Hash实例是否相等。
9. toString()：重写toString方法，将字节数组转换为十六进制字符串。
10. hashCode()：重写hashCode方法，返回字节数组的哈希码。
11. getBytes()：返回内部字节数组。

关键变量：主要变量为bytes，用于存储原始哈希字节数组。

相互依赖：与其他系统组件的交互主要通过调用MessageDigest和SM3Digest实例来计算哈希值。

核心操作 vs. 辅助操作：主要操作包括创建Sha256Hash实例、计算哈希值等核心功能，辅助操作包括重写equals、hashCode等辅助功能。

操作序列：操作序列包括创建Sha256Hash实例、计算哈希值、比较哈希值等流程。

性能方面：需要考虑计算哈希值的性能消耗，尤其是对大文件的处理。

可重用性：该模块具有良好的可重用性，可以方便地在其他项目中进行适配和重用。

用途：主要用于处理SHA-256哈希值的计算和比较，可用作映射中的键。

假设：假设该模块在使用时会传入正确的参数，且哈希值长度为32字节。
## Flow Diagram [via mermaid]
```mermaid
flowchart
    st=>start: Start
    op1=>operation: Sha256Hash.java
    op2=>operation: flowchart
    op3=>operation: org.tron.plugins.utils
    op4=>operation: Google Inc.
    op5=>operation: Andreas Schildbach
    op6=>operation: Apache License, Version 2.0
    op7=>operation: http://www.apache.org/licenses/LICENSE-2.0
    op8=>operation: com.google.common.base.Preconditions
    op9=>operation: com.google.common.io.ByteStreams
    op10=>operation: com.google.common.primitives.Ints
    op11=>operation: java.io.File
    op12=>operation: java.io.FileInputStream
    op13=>operation: java.io.IOException
    op14=>operation: java.io.Serializable
    op15=>operation: java.security.MessageDigest
    op16=>operation: java.security.NoSuchAlgorithmException
    op17=>operation: org.bouncycastle.crypto.digests.SM3Digest
    op18=>operation: SHA-256
    op19=>operation: byte[]
    op20=>operation: wrap(byte[])
    op21=>operation: of(boolean, byte[])
    op22=>operation: of(boolean, File)
    op23=>operation: newDigest
    op24=>operation: newSM3Digest
    op25=>operation: hash(boolean, byte[])
    op26=>operation: hash(boolean, byte[], int, int)
    op27=>operation: equals(Object)
    op28=>operation: toString
    op29=>operation: hashCode
    op30=>operation: getBytes
    op31=>operation: compareTo(final Sha256Hash)
    e=>end: End

    st->op1->op2->op3->op4->op5->op6->op7->op8->op9->op10->op11->op12->op13->op14->op15->op16->op17->op18->op19->op20->op21->op22->op23->op24->op25->op26->op27->op28->op29->op30->op31->e
```
