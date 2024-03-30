## Module: WalletGrpcClient.java
模块名称: WalletGrpcClient.java

主要目标: 此模块的目的是提供与钱包服务进行交互的功能。

关键功能: 
1. queryAccount(byte[] address): 查询账户信息。
2. createTransaction(TransferContract contract): 创建交易。
3. createTransferAssetTransaction(TransferAssetContract contract): 创建资产转移交易。
4. createParticipateAssetIssueTransaction(ParticipateAssetIssueContract contract): 创建参与资产发行交易。
5. createAssetIssue(AssetIssueContract contract): 创建资产发行交易。
6. voteWitnessAccount(VoteWitnessContract contract): 投票见证账户。
7. createWitness(WitnessCreateContract contract): 创建见证人。
8. broadcastTransaction(Transaction signedTransaction): 广播交易。
9. getBlock(long blockNum): 获取区块信息。
10. listNodes(): 列出节点信息。
11. getAssetIssueByAccount(byte[] address): 根据账户获取资产发行信息。
12. getAssetIssueByName(String assetName): 根据资产名称获取资产发行信息。
13. getAssetIssueListByName(String assetName): 根据资产名称获取资产发行列表。
14. getAssetIssueById(String assetId): 根据资产ID获取资产发行信息。

关键变量: 
- channel: 管理通道。
- walletBlockingStub: 钱包服务阻塞存根。

相互依赖: 该模块依赖于gRPC通信协议和钱包服务。

核心操作 vs. 辅助操作: 核心操作包括创建交易、广播交易等，辅助操作包括获取区块信息、列出节点信息等。

操作顺序: 模块提供了一系列操作方法，可以根据需求依次调用。

性能方面: 考虑到网络通信性能和数据处理效率。

可重用性: 该模块具有良好的可重用性，可以在不同项目中灵活使用。

用法: 该模块用于与钱包服务进行交互，实现账户查询、交易创建、资产发行等功能。

假设: 假设模块与其他系统组件正常交互，且数据格式符合预期。
## Flow Diagram [via mermaid]
```mermaid
flowchart
    st=>start: Start
    e=>end: End
    op1=>operation: queryAccount
    op2=>operation: createTransaction
    op3=>operation: createTransferAssetTransaction
    op4=>operation: createParticipateAssetIssueTransaction
    op5=>operation: createAssetIssue
    op6=>operation: voteWitnessAccount
    op7=>operation: createWitness
    op8=>operation: broadcastTransaction
    op9=>operation: getBlock
    op10=>operation: listNodes
    op11=>operation: getAssetIssueByAccount
    op12=>operation: getAssetIssueByName
    op13=>operation: getAssetIssueListByName
    op14=>operation: getAssetIssueById

    st->op1
    op1->op2
    op2->op8
    op2->e
    op1->op9
    op9->op10
    op10->e
    op1->op11
    op11->e
    op11->op13
    op13->e
    op12->e
    op12->op14
    op14->e
    op1->op3
    op3->e
    op1->op4
    op4->e
    op1->op5
    op5->e
    op1->op6
    op6->e
    op1->op7
    op7->e
```
