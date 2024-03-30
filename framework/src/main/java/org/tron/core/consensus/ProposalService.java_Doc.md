## Module: ProposalService.java
模块名称：ProposalService.java

主要目标：该模块的主要目标是处理提案相关的操作。

关键功能：主要方法/函数及其作用包括：
- process(Manager manager, ProposalCapsule proposalCapsule)：处理提案，根据提案类型执行相应操作。

关键变量：重要变量包括：
- manager：管理器对象，用于管理动态属性存储。
- proposalCapsule：提案胶囊对象，包含提案参数映射。

相互依赖：与其他系统组件的交互包括：
- ProposalUtil：继承自ProposalUtil类，提供提案相关的实用功能。

核心与辅助操作：主要操作为处理提案，辅助操作包括获取提案类型和执行相应操作。

操作序列：处理提案的操作依次根据提案类型进行执行。

性能方面：需考虑执行提案操作对系统性能的影响。

可重用性：该模块具有一定的可重用性，可以根据不同的提案类型进行扩展和适应。

用法：通常用于在区块链系统中处理提案相关的参数更新和设置。

假设：假设提案类型在validator方法中已定义，并且提案处理方法已实现。
## Flow Diagram [via mermaid]
```mermaid
graph TD
    A[ProposalService.java] -->|Processes proposal| B(Manager)
    B -->|Saves maintenance time interval| C(DynamicPropertiesStore)
    B -->|Saves account upgrade cost| C
    B -->|Saves create account fee| C
    B -->|Saves transaction fee| C
    B -->|Saves asset issue fee| C
    B -->|Saves witness pay per block| C
    B -->|Saves witness standby allowance| C
    B -->|Saves create new account fee in system contract| C
    B -->|Saves create new account bandwidth rate| C
    B -->|Saves allow creation of contracts| C
    B -->|Saves remove the power of the gr| C
    B -->|Saves energy fee| C
    B -->|Saves exchange create fee| C
    B -->|Saves max CPU time of one TX| C
    B -->|Saves allow update account name| C
    B -->|Saves allow same token name| C
    B -->|Saves allow delegate resource| C
    B -->|Saves total energy limit| C
    B -->|Saves allow TVM transfer TRC10| C
    B -->|Saves total current energy limit| C
    B -->|Saves allow multi sign| C
    B -->|Saves allow adaptive energy| C
    B -->|Saves update account permission fee| C
    B -->|Saves multi sign fee| C
    B -->|Saves allow proto filter num| C
    B -->|Saves allow account state root| C
    B -->|Saves allow TVM Constantinople| C
    B -->|Saves allow TVM Solidity 059| C
    B -->|Saves adaptive resource limit target ratio| C
    B -->|Saves adaptive resource limit multiplier| C
    B -->|Saves allow change delegation| C
    B -->|Saves witness 127 pay per block| C
    B -->|Saves forbid transfer to contract| C
    B -->|Saves allow PBFT| C
    B -->|Saves allow TVM Istanbul| C
    B -->|Saves allow shielded TRC20 transaction| C
    B -->|Saves allow market transaction| C
    B -->|Saves market sell fee| C
    B -->|Saves market cancel fee| C
    B -->|Saves max fee limit| C
    B -->|Saves allow transaction fee pool| C
    B -->|Saves allow black hole optimization| C
    B -->|Saves allow new resource model| C
    B -->|Saves allow TVM freeze| C
    B -->|Saves allow TVM vote| C
    B -->|Saves allow TVM London| C
    B -->|Saves allow TVM compatible EVM| C
    B -->|Saves free net limit| C
    B -->|Saves total net limit| C
    B -->|Saves allow account asset optimization| C
    B -->|Saves allow higher limit for max CPU time of one TX| C
    B -->|Saves allow asset optimization| C
    B -->|Saves allow new reward| C
    B -->|Saves memo fee| C
    B -->|Saves unfreeze delay days| C
    B -->|Saves allow delegate optimization| C
    B -->|Saves allow optimized return value of chain ID| C
    B -->|Saves allow dynamic energy| C
    B -->|Saves dynamic energy threshold| C
    B -->|Saves dynamic energy increase factor| C
    B -->|Saves dynamic energy max factor| C
    B -->|Saves allow TVM Shanghai| C
    B -->|Saves allow cancel all unfreeze V2| C
    B -->|Saves max delegate lock period| C
    B -->|Saves allow old reward opt| C
```
