package org.tron.common.runtime;

import org.tron.core.db.TransactionContext;
import org.tron.core.exception.ContractExeException;
import org.tron.core.exception.ContractValidateException;

/**
 * 定义了一个处理交易执行和智能合约执行的接口。
 *
 * 其目的是为了提供一个统一的执行环境，处理与交易执行相关的逻辑，包括合约验证和执行异常。
 */

/**
 * 核心与辅助操作：execute方法是核心操作，负责实际的交易执行逻辑。getResult和getRuntimeError可以视为辅助操作，用于获取执行结果和错误信息。
 *
 * 操作序列：通常，首先会调用execute方法执行交易，执行过程中可能会抛出异常。执行完成后，可以通过getResult获取执行结果，如果需要错误信息，可以调用getRuntimeError。
 *
 * 性能方面：执行效率和资源消耗（如计算能量消耗）是性能考虑的重点。异常处理机制也可能对性能有一定影响。
 *
 * 可重用性：作为一个接口，Runtime定义了一套标准的执行环境，可以被不同的实现所复用，以适应不同的执行逻辑需求。
 *
 * 使用情况：该接口被实现用于处理交易和智能合约的执行。具体的实现类会根据org.tron区块链平台的需要来执行相应的逻辑。
 */
public interface Runtime {

  //负责执行交易
  void execute(TransactionContext context)
      throws ContractValidateException, ContractExeException;

  //返回一个ProgramResult对象，包含执行结果的详细信息，如状态、消耗的能量等。
  ProgramResult getResult();

  //如果在执行过程中发生错误，此方法可以获取到具体的错误信息字符串。
  String getRuntimeError();

}
