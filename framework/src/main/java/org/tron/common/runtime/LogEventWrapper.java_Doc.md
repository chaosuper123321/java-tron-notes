## Module: LogEventWrapper.java
模块名称：LogEventWrapper.java

主要目标：该模块的目的是封装日志事件，提供对智能合约日志事件的处理和解析功能。

关键函数：主要方法包括getEventSignatureFull()，用于获取完整的事件签名；构造函数LogEventWrapper()，用于初始化对象。

关键变量：重要的变量包括topicList用于存储主题列表，data用于存储数据，eventSignature用于存储事件签名，abiEntry用于存储ABI条目。

相互依赖：该模块与智能合约日志过滤器、虚拟机工具、智能合约等组件有交互。

核心与辅助操作：主要操作是解析事件签名和构造完整事件签名，辅助操作包括初始化对象和处理参数列表。

操作序列：操作序列包括初始化对象、解析事件签名、构造完整事件签名。

性能考虑：性能方面需要考虑对大量日志事件的快速解析和处理。

可重用性：该模块具有良好的可重用性，可以在不同的智能合约系统中使用。

使用：该模块用于处理智能合约的日志事件，提供事件签名解析和构造功能。

假设：假设该模块需要与其他智能合约相关组件配合使用，如日志过滤器和ABI条目。
## Flow Diagram [via mermaid]
```mermaid
graph LR
    A[LogEventWrapper.java] --> B{ContractTrigger}
    B --> C[List<byte[]> topicList]
    B --> D[byte[] data]
    B --> E[String eventSignature]
    B --> F[SmartContract.ABI.Entry abiEntry]
    B --> G[LogEventWrapper]
    G --> H[getEventSignatureFull]
    H --> I[Objects.isNull(abiEntry)]
    I --> J[fallback]
    I --> K[sb = new StringBuilder]
    K --> L[append(abiEntry.getName)]
    L --> M[append(()]
    M --> N[sbp = new StringBuilder]
    N --> O[for (Param param : abiEntry.getInputsList)]
    O --> P[if (sbp.length > 0)]
    P --> Q[sbp.append(,)]
    Q --> R[sbp.append(param.getType)]
    R --> S[if (MUtil.isNotNullOrEmpty(param.getName))]
    S --> T[sbp.append( ).append(param.getName)]
    T --> O
    M --> U[sb.append(sbp.toString).append())]
    U --> V[sb.toString]
```
