## Module: DelegationStore.java
- **模块名称**: DelegationStore.java

- **主要目标**: 该模块的目的是管理和存储与代理投票、奖励分配、见证人投票等相关的数据。

- **关键函数**:
  - `addReward`: 为指定周期和地址添加奖励。
  - `getReward`: 获取指定周期和地址的奖励。
  - `setBeginCycle` / `getBeginCycle`: 设置和获取地址的开始周期。
  - `setEndCycle` / `getEndCycle`: 设置和获取地址的结束周期。
  - `setWitnessVote` / `getWitnessVote`: 设置和获取见证人投票。
  - `setAccountVote` / `getAccountVote`: 设置和获取账户投票。
  - `setBrokerage` / `getBrokerage`: 设置和获取佣金比例。
  - `setWitnessVi` / `getWitnessVi`: 设置和获取见证人的VI值。
  - `accumulateWitnessVi`: 累计见证人的VI值。

- **关键变量**:
  - `REMARK`: 特殊值，用于标记。
  - `DEFAULT_BROKERAGE`: 默认的佣金比例。
  - `DECIMAL_OF_VI_REWARD`: VI奖励的小数位。

- **相互依赖性**: 该模块依赖于`TronStoreWithRevoking`进行数据的存储和回滚，同时也依赖于`AccountCapsule`和`BytesCapsule`来封装数据。

- **核心与辅助操作**: 核心操作包括奖励的添加和获取、周期的设置和获取、投票的设置和获取。辅助操作包括佣金比例和VI值的设置和获取。

- **操作序列**: 通常，操作序列遵循设置周期 -> 添加/获取奖励 -> 设置/获取投票 -> 累计VI值的流程。

- **性能方面**: 性能考虑包括高效的数据存取和更新，以及在高并发环境下保持数据的一致性和完整性。

- **可重用性**: 该模块设计了灵活的接口和封装，易于在不同的上下文中重用和扩展。

- **使用**: 该模块被用于处理与代理投票相关的数据存储和管理，支持奖励分配、见证人投票等功能。

- **假设**:
  - 假设所有输入的地址和周期都是有效的。
  - 假设系统中的奖励、投票等操作都遵循特定的规则和约束。
## Flow Diagram [via mermaid]
```mermaid
flowchart LR
    subgraph DelegationStore [DelegationStore]
    direction TB

    getReward[getReward(cycle, address)]
    addReward[addReward(cycle, address, value)]
    setBeginCycle[setBeginCycle(address, number)]
    getBeginCycle[getBeginCycle(address)]
    setEndCycle[setEndCycle(address, number)]
    getEndCycle[getEndCycle(address)]
    setWitnessVote[setWitnessVote(cycle, address, value)]
    getWitnessVote[getWitnessVote(cycle, address)]
    setAccountVote[setAccountVote(cycle, address, accountCapsule)]
    getAccountVote[getAccountVote(cycle, address)]
    setBrokerage[setBrokerage(cycle, address, brokerage)]
    getBrokerage[getBrokerage(cycle, address)]
    setWitnessVi[setWitnessVi(cycle, address, value)]
    getWitnessVi[getWitnessVi(cycle, address)]
    accumulateWitnessVi[accumulateWitnessVi(cycle, address, voteCount)]

    DataStore[(Data Store)]
    
    getReward --> DataStore
    addReward --> DataStore
    setBeginCycle --> DataStore
    getBeginCycle --> DataStore
    setEndCycle --> DataStore
    getEndCycle --> DataStore
    setWitnessVote --> DataStore
    getWitnessVote --> DataStore
    setAccountVote --> DataStore
    getAccountVote --> DataStore
    setBrokerage --> DataStore
    getBrokerage --> DataStore
    setWitnessVi --> DataStore
    getWitnessVi --> DataStore
    accumulateWitnessVi --> DataStore

    end
```
