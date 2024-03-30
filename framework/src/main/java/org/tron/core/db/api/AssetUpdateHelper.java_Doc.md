## Module: AssetUpdateHelper.java
模块名称：AssetUpdateHelper.java

主要目标：该模块的目的是更新资产信息，包括资产发行、交易所和账户等。

关键功能：主要方法和功能包括：
1. doWork()：执行资产更新的主要方法，包括初始化、更新资产、更新交易所、更新账户等操作。
2. init()：初始化操作，重置资产发行和交易所存储，保存Token ID数量。
3. getAllAssetIssues()：获取所有资产发行信息的方法，遍历区块链中的交易，提取资产发行合约信息。
4. updateAsset()：更新资产信息的方法，包括生成新的Token ID，更新资产存储等操作。
5. updateExchange()：更新交易所信息的方法，将交易所中的Token ID映射为新的Token ID。
6. updateAccount()：更新账户信息的方法，将账户中的资产信息映射为新的Token ID，更新账户存储。

关键变量：重要的变量包括ChainBaseManager、assetNameToIdMap、tokenIdNum等。

相互依赖：该模块与ChainBaseManager、AssetIssueCapsule、ExchangeCapsule、AccountCapsule等组件相互交互，实现资产信息的更新。

核心与辅助操作：主要操作包括更新资产、更新交易所、更新账户等核心操作，辅助操作包括初始化、获取资产发行信息等。

操作序列：具有明显的操作流程，包括初始化、更新资产、更新交易所、更新账户等步骤。

性能考虑：需要考虑处理大量资产信息的性能，如优化循环遍历、减少数据库操作等。

可重用性：该模块具有一定的可重用性，可以用于不同的区块链系统中更新资产信息。

用途：主要用于更新区块链系统中的资产信息，包括资产发行、交易所和账户等。

假设：假设该模块需要在已有的区块链系统中运行，依赖于ChainBaseManager等组件的正常运行。
## Flow Diagram [via mermaid]
```mermaid
flowchart TD
    A[AssetUpdateHelper.java] -->|imports| B[org.tron.core.db.api]
    A -->|imports| C[org.tron.core.config.Parameter.ChainSymbol.TRX_SYMBOL_BYTES]
    A -->|imports| D[ArrayList]
    A -->|imports| E[Arrays]
    A -->|imports| F[HashMap]
    A -->|imports| G[Iterator]
    A -->|imports| H[List]
    A -->|imports| I[Map]
    A -->|imports| J[Entry]
    A -->|imports| K[log]
    A -->|imports| L[ByteArray]
    A -->|imports| M[ChainBaseManager]
    A -->|imports| N[AccountCapsule]
    A -->|imports| O[AssetIssueCapsule]
    A -->|imports| P[BlockCapsule]
    A -->|imports| Q[ExchangeCapsule]
    A -->|imports| R[TransactionCapsule]
    A -->|imports| S[ContractType]
    A -->|imports| T[AssetIssueContract]
    A -->|lombok.extern.slf4j| U[DB]
    A -->|private ChainBaseManager chainBaseManager;| V[HashMap<String, byte[]> assetNameToIdMap = new HashMap<>;]
    A -->|public AssetUpdateHelper(ChainBaseManager chainBaseManager)| W[this.chainBaseManager = chainBaseManager;]
    A -->|public void doWork| X[long start = System.currentTimeMillis;]
    A -->|public void init| Y[if (chainBaseManager.getAssetIssueV2Store.isNotEmpty) {]
    A -->|public List<AssetIssueCapsule> getAllAssetIssues| Z[List<AssetIssueCapsule> result = new ArrayList<>;]
    A -->|public void updateAsset| AA[long tokenIdNum = chainBaseManager.getDynamicPropertiesStore.getTokenIdNum;]
    A -->|public void updateExchange| BB[for (ExchangeCapsule exchangeCapsule : chainBaseManager.getExchangeStore.getAllExchanges) {]
    A -->|public void updateAccount| CC[Iterator<Entry<byte[], AccountCapsule>> iterator = chainBaseManager.getAccountStore.iterator;]
    A -->|public void finish| DD[chainBaseManager.getDynamicPropertiesStore.saveTokenUpdateDone(1);]
```
