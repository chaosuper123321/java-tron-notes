package org.tron.core.db;

import lombok.Data;
import org.tron.common.runtime.ProgramResult;
import org.tron.core.capsule.BlockCapsule;
import org.tron.core.capsule.TransactionCapsule;
import org.tron.core.store.StoreFactory;

//封装交易执行的上下文信息，
//提供一个执行环境的快照，
//使得交易的处理能够在一个清晰定义的环境中进行。
@Data
public class TransactionContext {
  //区块信息，类型为BlockCapsule。
  private BlockCapsule blockCap;
  //交易信息，类型为TransactionCapsule。
  private TransactionCapsule trxCap;
  //存储工厂，用于访问底层数据存储。
  private StoreFactory storeFactory;
  //程序执行结果，类型为ProgramResult。
  private ProgramResult programResult = new ProgramResult();
  //表示调用是否为静态。
  private boolean isStatic;
  //表示事件插件是否已加载。
  private boolean eventPluginLoaded;

  //初始化TransactionContext对象
  public TransactionContext(BlockCapsule blockCap, TransactionCapsule trxCap,
      StoreFactory storeFactory,
      boolean isStatic,
      boolean eventPluginLoaded) {
    this.blockCap = blockCap;
    this.trxCap = trxCap;
    this.storeFactory = storeFactory;
    this.isStatic = isStatic;
    this.eventPluginLoaded = eventPluginLoaded;
  }
}
