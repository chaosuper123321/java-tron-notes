## Module: ECKeyAgreement.java
- **模块名称**: ECKeyAgreement.java

- **主要目的**: 该模块的主要目的是为了实现椭圆曲线密钥协议（ECDH），以便在参与者之间安全地共享密钥材料。

- **关键功能**:
  - `getInstance()`: 获取KeyAgreement的实例，这是实现ECDH密钥协议的核心方法。它有三个重载版本，可以根据需要选择不同的提供者或使用默认提供者。
  
- **关键变量**:
  - `ALGORITHM`: 保存算法名称"ECDH"的字符串常量，用于在获取`KeyAgreement`实例时指定算法。
  
- **互相依赖**:
  - 该模块依赖于Java加密架构（JCA）提供的`KeyAgreement`类以及潜在的特定提供者实现，以实现ECDH算法。
  
- **核心 vs. 辅助操作**:
  - 核心操作是通过`getInstance`方法实现的ECDH密钥协议。
  - 辅助操作包括处理异常情况，如算法或提供者不可用时抛出的错误。
  
- **操作序列**:
  - 用户首先调用`getInstance`方法之一来获取`KeyAgreement`的实例。
  - 然后，用户可以使用此实例来执行ECDH密钥协议，从而安全地共享密钥材料。
  
- **性能方面**:
  - 性能考虑主要取决于底层JCA提供者的实现效率，以及所使用的椭圆曲线参数。
  
- **可重用性**:
  - 该模块设计为高度可重用，允许通过不同的提供者或默认提供者来获取ECDH密钥协议的实例。
  
- **使用**:
  - 该模块主要用于需要安全密钥交换的加密通信场景，如SSL/TLS协议、加密货币钱包之间的安全通信等。
  
- **假设**:
  - 该模块假设Java运行时环境（JRE）支持ECDH密钥协议。这是通过在无法获取`KeyAgreement`实例时抛出`AssertionError`来体现的。
## Flow Diagram [via mermaid]
```mermaid
graph TD
    A[Start] --> B[getInstance without provider]
    A --> C[getInstance with provider name]
    A --> D[getInstance with Provider object]
    B --> E[KeyAgreement.getInstance(ALGORITHM)]
    C --> F[KeyAgreement.getInstance(ALGORITHM, provider)]
    D --> G[KeyAgreement.getInstance(ALGORITHM, Provider object)]
    E --> H[Return KeyAgreement]
    F --> H
    G --> H
    H --> I[End]
```
