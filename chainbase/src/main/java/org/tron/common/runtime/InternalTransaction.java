/*
 * Copyright (c) [2016] [ <ether.camp> ]
 * This file is part of the ethereumJ library.
 *
 * The ethereumJ library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The ethereumJ library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with the ethereumJ library. If not, see <http://www.gnu.org/licenses/>.
 */
package org.tron.common.runtime;

import static org.apache.commons.lang3.ArrayUtils.isEmpty;
import static org.tron.common.utils.ByteUtil.EMPTY_BYTE_ARRAY;

import com.google.common.primitives.Longs;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import org.apache.commons.lang3.ArrayUtils;
import org.tron.common.crypto.Hash;
import org.tron.common.utils.WalletUtil;
import org.tron.core.capsule.ContractCapsule;
import org.tron.core.capsule.TransactionCapsule;
import org.tron.core.exception.ContractValidateException;
import org.tron.protos.Protocol.Transaction;
import org.tron.protos.contract.SmartContractOuterClass.CreateSmartContract;
import org.tron.protos.contract.SmartContractOuterClass.TriggerSmartContract;

/**
 * 该模块的目的是定义和处理以太坊J库中的内部交易，包括创建和调用智能合约。
 */


/**
 * 核心与辅助操作：
     核心操作包括交易的创建、数据的编码和哈希值的生成。
     辅助操作包括交易的拒绝标记和获取额外数据。
 * 操作序列：
     首先，根据交易类型（创建合约或调用合约）初始化内部交易。
     然后，根据需要设置交易数据和状态。
     最后，生成交易的哈希值和编码数据。
 * 性能方面：
     性能考虑主要集中在数据的编码和哈希计算上，需要优化以减少处理时间。
 * 可重用性：
     该模块设计为可重用，可以在处理不同交易和合约调用时复用。
 * 使用：
     用于以太坊J库中的智能合约创建和调用，以及交易的内部处理。
 */
public class InternalTransaction {

  private Transaction transaction;  //交易数据。
  private byte[] hash;              //交易的哈希值。
  private byte[] parentHash;        //父交易的哈希值。
  /* 转移的TRX数量（以sun计算）。 */
  private long value;

  //代币信息。
  private Map<String, Long> tokenInfo = new HashMap<>();

  /* the address of the destination account (for message)
   * In creation transaction the receive address is - 0 */
  private byte[] receiveAddress;    //接收地址。

  /* An unlimited size byte array specifying
   * input [data] of the message call or
   * Initialization code for a new contract */
  private byte[] data;  //消息调用的输入数据或新合约的初始化代码。
  private long nonce;   //用于存储交易的nonce值。
  private byte[] transferToAddress; //用于存储转账目标地址。

  /*  发送地址。 */
  private byte[] sendAddress;
  @Getter
  private int deep; //用于获取深度值。
  @Getter
  private int index;  //用于获取索引值。
  private boolean rejected; //用于表示是否被拒绝。
  private String note;  //用于存储备注信息。
  private byte[] protoEncoded;  //用于存储编码后的数据。

  /*
   * 用于记录投票见证操作码参数的额外数据字段
   */
  private String extra;


  /**
   * 初始化内部交易，包括根交易和子交易。
   */
  public InternalTransaction(Transaction trx, InternalTransaction.TrxType trxType)
      throws ContractValidateException {
    this.transaction = trx;
    TransactionCapsule trxCap = new TransactionCapsule(trx);
    this.protoEncoded = trxCap.getData();
    this.nonce = 0;
    // outside transaction should not have deep, so use -1 to mark it is root.
    // It will not count in vm trace. But this deep will be shown in program result.
    this.deep = -1;
    if (trxType == TrxType.TRX_CONTRACT_CREATION_TYPE) {
      CreateSmartContract contract = ContractCapsule.getSmartContractFromTransaction(trx);
      if (contract == null) {
        throw new ContractValidateException("Invalid CreateSmartContract Protocol");
      }
      this.sendAddress = contract.getOwnerAddress().toByteArray();
      this.receiveAddress = EMPTY_BYTE_ARRAY;
      this.transferToAddress = WalletUtil.generateContractAddress(trx);
      this.note = "create";
      this.value = contract.getNewContract().getCallValue();
      this.data = contract.getNewContract().getBytecode().toByteArray();
      this.tokenInfo.put(String.valueOf(contract.getTokenId()), contract.getCallTokenValue());
    } else if (trxType == TrxType.TRX_CONTRACT_CALL_TYPE) {
      TriggerSmartContract contract = ContractCapsule.getTriggerContractFromTransaction(trx);
      if (contract == null) {
        throw new ContractValidateException("Invalid TriggerSmartContract Protocol");
      }
      this.sendAddress = contract.getOwnerAddress().toByteArray();
      this.receiveAddress = contract.getContractAddress().toByteArray();
      this.transferToAddress = this.receiveAddress.clone();
      this.note = "call";
      this.value = contract.getCallValue();
      this.data = contract.getData().toByteArray();
      this.tokenInfo.put(String.valueOf(contract.getTokenId()), contract.getCallTokenValue());
    } else {
      // do nothing, just for running byte code
    }
    this.hash = trxCap.getTransactionId().getBytes();
  }

  /**
   * 初始化内部交易，包括根交易和子交易。
   */
  public InternalTransaction(byte[] parentHash, int deep, int index,
      byte[] sendAddress, byte[] transferToAddress, long value, byte[] data, String note,
      long nonce, Map<String, Long> tokenInfo) {
    this.parentHash = parentHash.clone();
    this.deep = deep;
    this.index = index;
    this.note = note;
    this.sendAddress = ArrayUtils.nullToEmpty(sendAddress);
    this.transferToAddress = ArrayUtils.nullToEmpty(transferToAddress);
    if ("create".equalsIgnoreCase(note)) {
      this.receiveAddress = EMPTY_BYTE_ARRAY;
    } else {
      this.receiveAddress = ArrayUtils.nullToEmpty(transferToAddress);
    }
    // in this case, value also can represent a tokenValue when tokenId is not null, otherwise it is a trx callvalue.
    this.value = value;
    this.data = ArrayUtils.nullToEmpty(data);
    this.nonce = nonce;
    this.hash = getHash();
    // in a contract call contract case, only one value should be used. trx or a token. can't be both. We should avoid using
    // tokenValue in this case.
    if (tokenInfo != null) {
      this.tokenInfo.putAll(tokenInfo);
    }
  }

  //获取交易信息。
  public Transaction getTransaction() {
    return transaction;
  }

  //设置交易信息。
  public void setTransaction(Transaction transaction) {
    this.transaction = transaction;
  }

  public byte[] getTransferToAddress() {
    return transferToAddress.clone();
  }

  //标记交易为拒绝。
  public void reject() {
    this.rejected = true;
  }

  //检查交易是否被拒绝。
  public boolean isRejected() {
    return rejected;
  }

  public String getNote() {
    if (note == null) {
      return "";
    }
    return note;
  }

  public Map<String, Long> getTokenInfo() {
    return tokenInfo;
  }

  public byte[] getSender() {
    if (sendAddress == null) {
      return EMPTY_BYTE_ARRAY;
    }
    return sendAddress.clone();
  }

  public byte[] getReceiveAddress() {
    if (receiveAddress == null) {
      return EMPTY_BYTE_ARRAY;
    }
    return receiveAddress.clone();
  }

  public byte[] getParentHash() {
    if (parentHash == null) {
      return EMPTY_BYTE_ARRAY;
    }
    return parentHash.clone();
  }

  public long getValue() {
    return value;
  }

  public void setValue(long value) {
    this.value= value;
  }

  public byte[] getData() {
    if (data == null) {
      return EMPTY_BYTE_ARRAY;
    }
    return data.clone();
  }

  public void setExtra(String extra) {
    this.extra = extra;
  }

  public String getExtra() {
    return extra == null ? "" : extra;
  }

  //获取交易的哈希值。
  public final byte[] getHash() {
    if (!isEmpty(hash)) {
      return Arrays.copyOf(hash, hash.length);
    }

    byte[] plainMsg = this.getEncoded();
    byte[] nonceByte;
    nonceByte = Longs.toByteArray(nonce);
    byte[] forHash = new byte[plainMsg.length + nonceByte.length];
    System.arraycopy(plainMsg, 0, forHash, 0, plainMsg.length);
    System.arraycopy(nonceByte, 0, forHash, plainMsg.length, nonceByte.length);
    this.hash = Hash.sha3(forHash);
    return Arrays.copyOf(hash, hash.length);
  }

  public long getNonce() {
    return nonce;
  }

  //获取编码后的交易信息。
  public byte[] getEncoded() {
    if (protoEncoded != null) {
      return protoEncoded.clone();
    }
    byte[] parentHashArray = parentHash.clone();

    if (parentHashArray == null) {
      parentHashArray = EMPTY_BYTE_ARRAY;
    }
    byte[] valueByte = Longs.toByteArray(this.value);
    byte[] raw = new byte[parentHashArray.length + this.receiveAddress.length + this.data.length
        + valueByte.length];
    System.arraycopy(parentHashArray, 0, raw, 0, parentHashArray.length);
    System
        .arraycopy(this.receiveAddress, 0, raw, parentHashArray.length, this.receiveAddress.length);
    System.arraycopy(this.data, 0, raw, parentHashArray.length + this.receiveAddress.length,
        this.data.length);
    System.arraycopy(valueByte, 0, raw,
        parentHashArray.length + this.receiveAddress.length + this.data.length,
        valueByte.length);
    this.protoEncoded = raw;
    return protoEncoded.clone();
  }

  public enum TrxType {
    TRX_PRECOMPILED_TYPE,       //预编译类型
    TRX_CONTRACT_CREATION_TYPE, //创建类型
    TRX_CONTRACT_CALL_TYPE,     //调用类型
    TRX_UNKNOWN_TYPE,           //未知类型
  }

  public enum ExecutorType {
    ET_PRE_TYPE,          //预执行类型
    ET_NORMAL_TYPE,       //普通执行类型
    ET_CONSTANT_TYPE,     //常量执行类型
    ET_UNKNOWN_TYPE,      //未知类型
  }

}
