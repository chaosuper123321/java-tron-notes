## Module: MultiLayoutPattern.java
模块名称：MultiLayoutPattern.java

**主要目标**：此模块的目的是提供一种灵活的日志布局方式，允许根据不同的日志记录器名称应用不同的布局模式。这样可以在同一个日志系统中针对不同的日志需求提供个性化的日志格式。

**关键函数**：
- `addRule(Rule rule)`：添加一个新的规则，用于定义不同日志记录器的布局。
- `encode(ILoggingEvent event)`：根据事件的记录器名称获取对应的布局，并将日志事件转换为字节序列。
- `getLayout(final String name)`：根据提供的记录器名称，获取相应的布局。如果没有找到匹配的布局，则使用默认布局。

**关键变量**：
- `List<Rule> rules`：存储所有的布局规则。
- `Map<String, Layout<ILoggingEvent>> layoutMap`：缓存根据记录器名称映射的布局，以提高查找效率。

**相互依赖性**：此模块依赖于Logback的日志结构，特别是`Logger`、`PatternLayoutEncoder`和`Layout<ILoggingEvent>`等类。它通过这些组件实现日志事件的布局和编码。

**核心与辅助操作**：核心操作包括规则的添加(`addRule`)和日志事件的编码(`encode`)。辅助操作包括字符集转换(`convertToBytes`)和布局获取(`getLayout`)。

**操作序列**：首先，通过`addRule`方法添加布局规则。当日志事件发生时，`encode`方法会被调用，该方法根据事件的记录器名称获取对应的布局，并将事件转换为字节序列。

**性能方面**：通过使用`layoutMap`缓存布局，可以减少查找布局的时间，从而提高编码性能。此外，字符集的选择也可能影响性能。

**可重用性**：这个模块的设计允许通过简单地添加新的规则来轻松扩展，使其具有很好的可重用性和适应性。

**使用**：此模块可以在需要对不同日志记录器应用不同布局模式的场景中使用，特别是在复杂的应用程序中，这种需求更为常见。

**假设**：假设所有的日志记录器名称都是已知的，并且每个记录器名称对应的布局在使用前都已经定义好。此外，还假设日志事件都是通过Logback框架进行处理的。
## Flow Diagram [via mermaid]
```mermaid
flowchart LR
    A[Client] -->|ILoggingEvent| B(encode)
    B -->|getLayout| D[getLayout]
    D -->|Layout<ILoggingEvent>| E
    B -->|convertToBytes| C[convertToBytes]
    C -->|byte[]| F[Client]
    E -->|Layout<ILoggingEvent>| B
    B -.->|byte[]| G[External System]
    
    classDef process fill:#f9f,stroke:#333,stroke-width:2px;
    classDef datastore fill:#fcf,stroke:#f66,stroke-width:2px,stroke-dasharray: 5, 5;
    classDef external fill:#bbf,stroke:#33f,stroke-width:2px;
    classDef flow fill:none,stroke:#333,stroke-width:2px,arrowhead:'vee',color:#333;

    class B process;
    class C,E process;
    class D datastore;
    class A,F, G external;
    class A,B,C,D,E,F flow;
```
