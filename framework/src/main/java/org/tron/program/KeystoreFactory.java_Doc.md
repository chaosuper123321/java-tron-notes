## Module: KeystoreFactory.java
模块：KeystoreFactory.java

主要目标：该模块的主要目的是生成和管理加密钱包的密钥库。

关键功能：主要方法/函数及其作用包括：
1. priKeyValid(String priKey)：验证私钥的有效性。
2. fileCheck(File file)：检查文件是否存在，并根据情况创建文件或目录。
3. genKeystore()：生成密钥库文件并输出相关信息。
4. importPrivateKey()：导入私钥并生成密钥库文件。
5. help()：显示可用命令列表和提示信息。
6. run()：运行用户输入的命令并执行相应操作。

关键变量：重要变量包括FilePath、password、eCkey、file、fileName、credentials等。

相互依赖：与其他系统组件的交互主要体现在密钥生成、文件操作和用户输入等方面。

核心与辅助操作：主要操作包括生成密钥库、导入私钥和显示帮助信息等，辅助操作包括文件检查和私钥验证等。

操作顺序：用户首先通过help()方法查看可用命令列表，然后根据提示输入相应命令执行对应操作。

性能方面：性能考虑主要集中在密钥生成和文件操作的效率上。

可重用性：该模块具有一定的可重用性，可以用于生成和管理不同加密货币的密钥库。

用途：主要用于生成、导入和管理加密货币的密钥库文件。

假设：假设用户能够正确输入私钥并设置密码，以及正确操作命令来执行相应操作。
## Flow Diagram [via mermaid]
```mermaid
flowchart
    st=>start: Start
    op1=>operation: Args.setParam(args, Constant.TESTNET_CONF)
    op2=>operation: KeystoreFactory cli = new KeystoreFactory
    op3=>operation: JCommander.newBuilder
        .addObject(cli)
        .build
        .parse(args)
    op4=>operation: cli.run
    e=>end: End

    st->op1->op2->op3->op4->e
```
