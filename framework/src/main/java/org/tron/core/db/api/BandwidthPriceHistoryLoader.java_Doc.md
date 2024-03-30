## Module: BandwidthPriceHistoryLoader.java
模块名称: BandwidthPriceHistoryLoader.java

主要目标: 该模块的目的是加载并保存带宽价格历史记录。

关键功能: 
- doWork(): 执行加载带宽价格历史记录的主要方法，包括获取带宽提案、解析提案并保存历史记录。
- getBandwidthProposals(): 获取批准的交易费用类型的带宽提案。
- parseProposalsToStr(): 将带宽提案解析为字符串。
- finish(): 完成加载并保存带宽价格历史记录。

关键变量: 
- chainBaseManager: 用于管理链基础操作的对象。
- proposalCapsuleList: 保存带宽提案的列表。

相互依赖: 该模块与ChainBaseManager、ProposalCapsule、DynamicPropertiesStore等系统组件进行交互。

核心操作 vs. 辅助操作: 核心操作包括加载带宽价格历史记录，而辅助操作包括获取带宽提案和解析提案为字符串。

操作序列: 
1. 执行doWork()方法开始加载带宽价格历史记录。
2. 调用getBandwidthProposals()获取带宽提案。
3. 调用parseProposalsToStr()解析带宽提案并保存历史记录。
4. 调用finish()完成加载并保存带宽价格历史记录。

性能考虑: 需要考虑加载和保存大量带宽价格历史记录的性能。

可重用性: 该模块具有较高的可重用性，可以用于加载和保存其他类型的历史记录。

用法: 通过创建BandwidthPriceHistoryLoader实例并调用doWork()方法来使用该模块。

假设: 假设ChainBaseManager、ProposalCapsule、DynamicPropertiesStore等系统组件已正确配置和初始化。
## Flow Diagram [via mermaid]
```mermaid
graph TD
    A(BandwidthPriceHistoryLoader.java)
    B[ChainBaseManager]
    C[ProposalCapsule]
    D[DynamicPropertiesStore]
    E{System.currentTimeMillis}
    F{logger.info}
    G{!proposalCapsuleList.isEmpty}
    H{parseProposalsToStr}
    I{chainBaseManager.getDynamicPropertiesStore.saveBandwidthPriceHistory(bandwidthPriceHistory)}
    J{finish}
    K{builder.append}
    L{chainBaseManager.getProposalStore}
    M[State.APPROVED, ProposalType.TRANSACTION_FEE.getCode]
    N{builder.toString}
    O{chainBaseManager.getDynamicPropertiesStore.saveBandwidthPriceHistoryDone(1)}

    A -->|chainBaseManager| B
    A -->|proposalCapsuleList| C
    A -->|logger.info| E
    A -->|getBandwidthProposals| G
    G -- Yes --> H
    G -- No --> J
    H -->|parseProposalsToStr| I
    I -->|bandwidthPriceHistory| D
    J -->|finish| O
    E -->|logger.info| F
    F -->|System.currentTimeMillis| A
    C -->|proposalCapsule.getExpirationTime| K
    C -->|proposalCapsule.getParameters.get(ProposalType.TRANSACTION_FEE.getCode)| K
    K -->|builder| N
    L -->|getSpecifiedProposals| M
    M -->|proposalCapsule| C
```
