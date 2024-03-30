## Module: FullViewingKey.java
模块名称：FullViewingKey.java

主要目标：FullViewingKey模块的主要目标是实现加密和解密操作，包括使用全视图密钥进行解密。

关键功能：主要方法和功能包括：
1. decode(byte[] data)：将字节数组数据解码为FullViewingKey对象。
2. inViewingKey()：生成IncomingViewingKey对象，用于处理传入的视图密钥。
3. isValid()：验证FullViewingKey对象是否有效。
4. encode()：将FullViewingKey对象编码为字节数组。

关键变量：关键变量包括ak（256位）、nk（256位）和ovk（256位）。

相互依赖性：FullViewingKey模块与JLibrustzcash和IncomingViewingKey模块有相互依赖关系，通过调用相关方法实现加密和解密操作。

核心与辅助操作：核心操作包括解码、验证和编码，辅助操作包括生成IncomingViewingKey对象。

操作序列：操作序列包括解码数据、生成IncomingViewingKey对象、验证FullViewingKey对象有效性和编码FullViewingKey对象。

性能方面：性能方面需要考虑数据处理的效率和准确性，以及对加密算法的性能要求。

重复使用性：FullViewingKey模块具有良好的可重复使用性，可以在不同的加密和解密场景中灵活应用。

用法：FullViewingKey模块可用于处理加密数据的解码和验证操作，适用于需要加密传输和存储数据的场景。

假设：假设FullViewingKey模块能够正确处理输入的加密数据，并能够生成有效的视图密钥。
