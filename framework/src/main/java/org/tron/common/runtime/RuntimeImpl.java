package org.tron.common.runtime;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.tron.common.parameter.CommonParameter;
import org.tron.core.actuator.Actuator;
import org.tron.core.actuator.Actuator2;
import org.tron.core.actuator.ActuatorCreator;
import org.tron.core.actuator.VMActuator;
import org.tron.core.db.TransactionContext;
import org.tron.core.exception.ContractExeException;
import org.tron.core.exception.ContractValidateException;
import org.tron.core.vm.program.Program;
import org.tron.core.vm.program.Program.BadJumpDestinationException;
import org.tron.core.vm.program.Program.IllegalOperationException;
import org.tron.core.vm.program.Program.JVMStackOverFlowException;
import org.tron.core.vm.program.Program.OutOfEnergyException;
import org.tron.core.vm.program.Program.OutOfMemoryException;
import org.tron.core.vm.program.Program.OutOfTimeException;
import org.tron.core.vm.program.Program.PrecompiledContractException;
import org.tron.core.vm.program.Program.StackTooLargeException;
import org.tron.core.vm.program.Program.StackTooSmallException;
import org.tron.protos.Protocol.Transaction.Contract.ContractType;
import org.tron.protos.Protocol.Transaction.Result.contractResult;

/**
 * 执行智能合约的验证和执行流程，处理与智能合约执行相关的异常，并返回执行结果。
 */
@Slf4j(topic = "VM")
public class RuntimeImpl implements Runtime {
  //TransactionContext对象，包含执行智能合约所需的所有上下文信息。
  TransactionContext context;
  //Actuator列表，用于普通合约的执行。
  private List<Actuator> actuatorList = null;
  //用于特定类型合约（如VMActuator）的执行。
  @Getter
  private Actuator2 actuator2 = null;

  //根据交易上下文执行智能合约，处理不同类型的合约执行或验证。
  @Override
  public void execute(TransactionContext context)
      throws ContractValidateException, ContractExeException {
    this.context = context;

    ContractType contractType = context.getTrxCap().getInstance().getRawData().getContract(0)
        .getType();
    switch (contractType.getNumber()) {
      case ContractType.TriggerSmartContract_VALUE:
      case ContractType.CreateSmartContract_VALUE:
        Set<String> actuatorSet = CommonParameter.getInstance().getActuatorSet();
        if (!actuatorSet.isEmpty() && !actuatorSet.contains(VMActuator.class.getSimpleName())) {
          throw new ContractValidateException("not exist contract " + "SmartContract");
        }
        actuator2 = new VMActuator(context.isStatic());
        break;
      default:
        actuatorList = ActuatorCreator.getINSTANCE().createActuator(context.getTrxCap());
    }
    if (actuator2 != null) {
      actuator2.validate(context);
      actuator2.execute(context);
    } else {
      for (Actuator act : actuatorList) {
        act.validate();
        act.execute(context.getProgramResult().getRet());
      }
    }

    setResultCode(context.getProgramResult());

  }

  //返回智能合约执行的结果。
  @Override
  public ProgramResult getResult() {
    return context.getProgramResult();
  }

  //获取执行过程中的运行时错误。
  @Override
  public String getRuntimeError() {
    return context.getProgramResult().getRuntimeError();
  }


  //根据程序执行结果和异常设置结果代码。
  private void setResultCode(ProgramResult result) {
    RuntimeException exception = result.getException();
    if (Objects.isNull(exception) && StringUtils
        .isEmpty(result.getRuntimeError()) && !result.isRevert()) {
      result.setResultCode(contractResult.SUCCESS);
      return;
    }
    if (result.isRevert()) {
      result.setResultCode(contractResult.REVERT);
      return;
    }
    if (exception instanceof IllegalOperationException) {
      result.setResultCode(contractResult.ILLEGAL_OPERATION);
      return;
    }
    if (exception instanceof OutOfEnergyException) {
      result.setResultCode(contractResult.OUT_OF_ENERGY);
      return;
    }
    if (exception instanceof BadJumpDestinationException) {
      result.setResultCode(contractResult.BAD_JUMP_DESTINATION);
      return;
    }
    if (exception instanceof OutOfTimeException) {
      result.setResultCode(contractResult.OUT_OF_TIME);
      return;
    }
    if (exception instanceof OutOfMemoryException) {
      result.setResultCode(contractResult.OUT_OF_MEMORY);
      return;
    }
    if (exception instanceof PrecompiledContractException) {
      result.setResultCode(contractResult.PRECOMPILED_CONTRACT);
      return;
    }
    if (exception instanceof StackTooSmallException) {
      result.setResultCode(contractResult.STACK_TOO_SMALL);
      return;
    }
    if (exception instanceof StackTooLargeException) {
      result.setResultCode(contractResult.STACK_TOO_LARGE);
      return;
    }
    if (exception instanceof JVMStackOverFlowException) {
      result.setResultCode(contractResult.JVM_STACK_OVER_FLOW);
      return;
    }
    if (exception instanceof Program.TransferException) {
      result.setResultCode(contractResult.TRANSFER_FAILED);
      return;
    }
    if (exception instanceof Program.InvalidCodeException) {
      result.setResultCode(contractResult.INVALID_CODE);
      return;
    }
    result.setResultCode(contractResult.UNKNOWN);
  }

}

