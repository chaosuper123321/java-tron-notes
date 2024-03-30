## Module: EnergyProcessor.java
**模块名称**：EnergyProcessor.java

**主要目标**：该模块的目的是处理与能源（Energy）相关的各种操作，包括更新使用量、计算全局能源限制以及处理能源消耗。

**关键函数**：
- `updateUsage`：更新账户的能源使用量。
- `updateTotalEnergyAverageUsage`：更新系统的平均能源使用量。
- `updateAdaptiveTotalEnergyLimit`：根据平均能源使用量调整能源的总限制。
- `consume`：处理交易时消耗能源的逻辑（当前抛出不支持的异常）。
- `useEnergy`：尝试使用指定量的能源，并更新相关统计。
- `calculateGlobalEnergyLimit` 和 `calculateGlobalEnergyLimitV2`：计算给定账户的全局能源限制。
- `getAccountLeftEnergyFromFreeze`：计算账户冻结资产剩余的能源量。

**关键变量**：
- `dynamicPropertiesStore` 和 `accountStore`：分别用于访问动态属性和账户信息的存储。

**交互依赖**：
- 与 `DynamicPropertiesStore` 和 `AccountStore` 强烈交互，用于读取和更新链上的动态属性和账户状态。

**核心与辅助操作**：
- 核心操作包括能源的更新、消耗和计算限制等。
- 辅助操作可能包括日志记录和异常处理。

**操作序列**：
1. 计算头部时段（`getHeadSlot`）。
2. 更新使用量或总平均使用量。
3. 调整能源限制或直接使用能源。
4. 根据需要更新账户和系统状态。

**性能考虑**：
- 性能上需要注意的是，能源计算和更新操作可能会频繁执行，因此优化数据库访问和算法效率是关键。

**可重用性**：
- 由于该模块专注于处理能源相关逻辑，其重用性可能局限于类似的区块链系统，特别是需要能源概念的系统。

**使用**：
- 主要在处理交易和账户状态更新时使用，以确保能源的合理分配和使用。

**假设**：
- 假设动态属性和账户状态存储是最新的且准确的。
- 假设系统配置（如能源消耗率和限制）已经适当设置。
## Flow Diagram [via mermaid]
```mermaid
flowchart LR
    A[Transaction Capsule] -->|Consumes Energy| B(Energy Processor)
    B -->|Updates Usage| C{Account Capsule}
    C -->|New Energy Usage| D[Dynamic Properties Store]
    D -->|Adjusts Energy Limits| E[Total Energy Limit]
    E -->|Global Energy Limit| F[Account Energy Limit]
    F -->|Left Energy| G[Account Left Energy From Freeze]

    B -.->|Updates| H[Block Energy Usage]
    H -.->|In Dynamic Properties Store| D

    B -.->|Calculates| I[Global Energy Limit]
    I -.->|Based on Froze Balance| C

    click B https://tron.network Energy Processor
    click C https://tron.network Account Capsule
    click D https://tron.network Dynamic Properties Store
```
