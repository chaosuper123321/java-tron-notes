package org.tron.common.runtime;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static org.apache.commons.collections4.CollectionUtils.size;
import static org.tron.common.utils.ByteUtil.EMPTY_BYTE_ARRAY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.tron.common.logsfilter.trigger.ContractTrigger;
import org.tron.common.runtime.vm.DataWord;
import org.tron.common.runtime.vm.LogInfo;
import org.tron.core.capsule.TransactionResultCapsule;
import org.tron.protos.Protocol.Transaction.Result.contractResult;

public class ProgramResult {

  /*
energyUsed：用于记录执行智能合约过程中消耗的能量。
hReturn：存储智能合约执行后返回的数据。
contractAddress：智能合约地址。
logInfoList：执行过程中产生的日志信息列表。
internalTransactions：内部交易列表。
   */
  private long energyUsed = 0;

  @Getter
  private long energyPenaltyTotal = 0;

  private byte[] hReturn = EMPTY_BYTE_ARRAY;
  private byte[] contractAddress = EMPTY_BYTE_ARRAY;
  private RuntimeException exception;
  private boolean revert;

  private Set<DataWord> deleteAccounts;
  private List<InternalTransaction> internalTransactions;
  private List<LogInfo> logInfoList;
  private TransactionResultCapsule ret = new TransactionResultCapsule();

  @Setter
  private List<ContractTrigger> triggerList;

  @Setter
  @Getter
  private String runtimeError;

  @Getter
  @Setter
  private contractResult resultCode;

  /*
   * for testing runs ,
   * call/create is not executed
   * but dummy recorded
   */
  private List<CallCreate> callCreateList;

  public static ProgramResult createEmpty() {
    ProgramResult result = new ProgramResult();
    result.setHReturn(EMPTY_BYTE_ARRAY);
    return result;
  }

  //计算执行智能合约过程中消耗的能量。
  public void spendEnergy(long energy) {
    energyUsed += energy;
  }

  //计算执行智能合约过程中消耗的能量。
  public void spendEnergyWithPenalty(long total, long penalty) {
    energyPenaltyTotal += penalty;
    energyUsed += total;
  }

  //设置智能合约是否被回滚。
  public void setRevert() {
    this.revert = true;
  }

  //检查智能合约是否被回滚。
  public boolean isRevert() {
    return revert;
  }

  public void refundEnergy(long energy) {
    energyUsed -= energy;
  }

  public void addTotalPenalty(long penalty) {
    energyPenaltyTotal += penalty;
  }

  public byte[] getContractAddress() {
    return Arrays.copyOf(contractAddress, contractAddress.length);
  }

  public void setContractAddress(byte[] contractAddress) {
    this.contractAddress = Arrays.copyOf(contractAddress, contractAddress.length);
  }

  public byte[] getHReturn() {
    return hReturn;
  }

  public void setHReturn(byte[] hReturn) {
    this.hReturn = hReturn;

  }

  public List<ContractTrigger> getTriggerList() {
    return triggerList != null ? triggerList : new LinkedList<>();
  }

  public TransactionResultCapsule getRet() {
    return ret;
  }

  public void setRet(TransactionResultCapsule ret) {
    this.ret = ret;
  }

  public RuntimeException getException() {
    return exception;
  }

  public void setException(RuntimeException exception) {
    this.exception = exception;
  }

  public long getEnergyUsed() {
    return energyUsed;
  }

  ////处理和存储在执行过程中需要删除的账户。
  public Set<DataWord> getDeleteAccounts() {
    if (deleteAccounts == null) {
      deleteAccounts = new HashSet<>();
    }
    return deleteAccounts;
  }

  //处理和存储在执行过程中需要删除的账户。
  public void addDeleteAccount(DataWord address) {
    getDeleteAccounts().add(address);
  }

  ////处理和存储在执行过程中需要删除的账户。
  public void addDeleteAccounts(Set<DataWord> accounts) {
    if (!isEmpty(accounts)) {
      getDeleteAccounts().addAll(accounts);
    }
  }

  //获取执行过程中产生的日志信息。
  public List<LogInfo> getLogInfoList() {
    if (logInfoList == null) {
      logInfoList = new ArrayList<>();
    }
    return logInfoList;
  }

  //添加执行过程中产生的日志信息。
  public void addLogInfo(LogInfo logInfo) {
    getLogInfoList().add(logInfo);
  }

  //添加执行过程中产生的日志信息。
  public void addLogInfos(List<LogInfo> logInfos) {
    if (!isEmpty(logInfos)) {
      getLogInfoList().addAll(logInfos);
    }
  }

  public List<CallCreate> getCallCreateList() {
    if (callCreateList == null) {
      callCreateList = new ArrayList<>();
    }
    return callCreateList;
  }

  public void addCallCreate(byte[] data, byte[] destination, byte[] energyLimit, byte[] value) {
    getCallCreateList().add(new CallCreate(data, destination, energyLimit, value));
  }

  //获取内部交易列表。
  public List<InternalTransaction> getInternalTransactions() {
    if (internalTransactions == null) {
      internalTransactions = new ArrayList<>();
    }
    return internalTransactions;
  }

  //添加内部交易列表。
  public InternalTransaction addInternalTransaction(byte[] parentHash, int deep,
      byte[] senderAddress, byte[] transferAddress, long value, byte[] data, String note,
      long nonce, Map<String, Long> token) {
    InternalTransaction transaction = new InternalTransaction(parentHash, deep,
        size(internalTransactions), senderAddress, transferAddress, value, data, note, nonce,
        token);
    getInternalTransactions().add(transaction);
    return transaction;
  }

  //添加内部交易列表。
  public void addInternalTransaction(InternalTransaction internalTransaction) {
    getInternalTransactions().add(internalTransaction);
  }

  //添加内部交易列表。
  public void addInternalTransactions(List<InternalTransaction> internalTransactions) {
    getInternalTransactions().addAll(internalTransactions);
  }

  public void rejectInternalTransactions() {
    for (InternalTransaction internalTx : getInternalTransactions()) {
      internalTx.reject();
    }
  }

  public void reset() {
    getDeleteAccounts().clear();
    getLogInfoList().clear();
  }

  //合并另一个ProgramResult的结果到当前实例。
  public void merge(ProgramResult another) {
    addInternalTransactions(another.getInternalTransactions());
    addTotalPenalty(another.getEnergyPenaltyTotal());
    if (another.getException() == null && !another.isRevert()) {
      addDeleteAccounts(another.getDeleteAccounts());
      addLogInfos(another.getLogInfoList());
    }
  }

}
