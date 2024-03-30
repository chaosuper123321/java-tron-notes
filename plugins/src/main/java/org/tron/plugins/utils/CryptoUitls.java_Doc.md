## Module: CryptoUitls.java
- **模块名称**: CryptoUtils.java
- **主要目标**: 该模块的目的是提供加密工具功能。
- **关键功能**: 
    - `ECKey_ENGINE`：定义了一个常量字符串，用于标识ECKey引擎。
- **关键变量**: 
    - `ECKey_ENGINE`：存储了ECKey引擎的名称。
- **相互依赖性**: 该模块可能与其他系统组件进行交互，如加密算法库或其他工具类。
- **核心与辅助操作**: 
    - 核心操作是定义和存储加密引擎的名称。
    - 辅助操作可能包括其他与加密相关的功能。
- **操作序列**: 该模块主要用于定义和存储加密引擎的名称，没有明显的操作序列。
- **性能方面**: 由于只是定义一个常量字符串，性能方面不会有太大影响。
- **可重用性**: 该模块可以轻松地被其他部分调用，以获取ECKey引擎的名称。
- **用法**: 可以通过调用`ECKey_ENGINE`常量来获取ECKey引擎的名称。
- **假设**: 假设该模块是为了方便在代码中引用ECKey引擎的名称而创建的。
## Flow Diagram [via mermaid]
```mermaid
flowchart
    st=>start: Start
    op=>operation: CryptoUitls.java
    sub1=>subroutine: org.tron.plugins.utils
    sub2=>subroutine: public class CryptoUitls
    sub3=>subroutine: public static final String ECKey_ENGINE = ECKey
    e=>end: End

    st->op->sub1->sub2->sub3->e
```
