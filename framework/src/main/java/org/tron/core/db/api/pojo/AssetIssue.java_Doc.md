## Module: AssetIssue.java
- **模块名称**: AssetIssue.java
- **主要目标**: 该模块的目的是定义资产发行的相关信息。
- **关键功能**: 
   - `getName()`: 返回资产名称。
   - `getAddress()`: 返回资产地址。
   - `getStart()`: 返回资产发行开始时间。
   - `getEnd()`: 返回资产发行结束时间。
- **关键变量**: 
   - `name`: 资产名称。
   - `address`: 资产地址。
   - `start`: 资产发行开始时间。
   - `end`: 资产发行结束时间。
- **相互依赖性**: 该模块与其他系统组件的交互主要是提供资产发行的相关信息。
- **核心与辅助操作**: 核心操作是获取资产发行的信息，辅助操作可能包括验证和处理数据。
- **操作顺序**: 模块的操作顺序可能是首先获取资产名称和地址，然后确定发行的时间范围。
- **性能方面**: 模块的性能考虑可能包括对大量资产信息的快速处理。
- **可重用性**: 该模块可以轻松适应不同的资产发行需求，具有良好的可重用性。
- **用法**: 通常用于获取和处理资产发行的相关信息。
- **假设**: 可能的假设是该模块用于管理已知的资产发行信息。
## Flow Diagram [via mermaid]
```mermaid
flowchart
    st=>start: Start
    op1=>operation: AssetIssue.java
    op2=>operation: package org.tron.core.db.api.pojo;
    op3=>operation: import lombok.Data;
    op4=>operation: @Data(staticConstructor = of)
    op5=>operation: public class AssetIssue {
    op6=>operation: private String name;
    op7=>operation: private String address;
    op8=>operation: private long start;
    op9=>operation: private long end;
    e=>end: End

    st->op1->op2->op3->op4->op5->op6->op7->op8->op9->e
```
