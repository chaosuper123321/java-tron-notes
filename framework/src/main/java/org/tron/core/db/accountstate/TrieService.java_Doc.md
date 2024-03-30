## Module: TrieService.java
模块名称: TrieService.java

主要目标: 该模块的目的是管理区块链账户状态的存储和检索。

关键功能: 
1. getFullAccountStateRootHash(): 获取完整账户状态的根哈希。
2. getSolidityAccountStateRootHash(): 获取Solidity账户状态的根哈希。
3. getAccountStateRootHash(long blockNumber): 获取特定区块号的账户状态根哈希。

关键变量: 
1. chainBaseManager: 区块链基础管理器。
2. accountStateStoreTrie: 账户状态存储Trie。
3. latestNumber: 最新区块号。
4. latestSolidityNumber: 最新Solidity区块号。
5. rootHash: 账户状态根哈希值。

相互依赖: 该模块依赖ChainBaseManager和AccountStateStoreTrie来获取和存储账户状态信息。

核心操作 vs. 辅助操作: 核心操作包括获取账户状态根哈希值，辅助操作包括处理异常情况。

操作序列: 
1. 调用getFullAccountStateRootHash()或getSolidityAccountStateRootHash()方法。
2. 内部调用getAccountStateRootHash()方法获取账户状态根哈希。

性能方面: 考虑到对区块链数据的访问和处理，性能方面需要高效处理账户状态信息。

可重用性: 该模块可以轻松适应不同的区块链系统，并可重复使用。

用法: TrieService模块用于获取和处理区块链账户状态的根哈希信息。

假设: 
1. 假设ChainBaseManager和AccountStateStoreTrie已经正确配置和初始化。
2. 假设getBlockByNum()方法能够正确获取指定区块号的区块信息。
## Flow Diagram [via mermaid]
```mermaid
flowchart
    st=>start: Start
    op1=>operation: getFullAccountStateRootHash
    op2=>operation: getAccountStateRootHash
    op3=>operation: getSolidityAccountStateRootHash
    e=>end: End
    
    st->op1
    op1->op2
    op1->e
    op2->e
    op3->e
```
