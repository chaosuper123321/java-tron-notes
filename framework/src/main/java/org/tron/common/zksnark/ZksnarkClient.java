package org.tron.common.zksnark;

import com.google.protobuf.ByteString;
import io.grpc.ManagedChannelBuilder;
import org.tron.api.TronZksnarkGrpc;
import org.tron.api.ZksnarkGrpcAPI.ZksnarkRequest;
import org.tron.api.ZksnarkGrpcAPI.ZksnarkResponse.Code;
import org.tron.core.capsule.TransactionCapsule;
import org.tron.protos.Protocol.Transaction;

/**
 * 首先通过getInstance方法获取ZksnarkClient的实例，
 * 使用checkZksnarkProof方法来检查特定交易的Zksnark证明。
 */
public class ZksnarkClient {

  //ZksnarkClient的单例实例。
  public static final ZksnarkClient instance = new ZksnarkClient();

  //用于与gRPC服务进行通信的阻塞存根。
  private TronZksnarkGrpc.TronZksnarkBlockingStub blockingStub;

  public ZksnarkClient() {
    blockingStub = TronZksnarkGrpc.newBlockingStub(ManagedChannelBuilder
        .forTarget("127.0.0.1:60051")
        .usePlaintext()
        .build());
  }

  //获取ZksnarkClient的实例。
  public static ZksnarkClient getInstance() {
    return instance;
  }

  //检查给定的Zksnark证明是否有效。它需要交易信息、签名哈希和价值平衡作为参数。
  public boolean checkZksnarkProof(Transaction transaction, byte[] sighash, long valueBalance) {
    String txId = new TransactionCapsule(transaction).getTransactionId().toString();
    ZksnarkRequest request = ZksnarkRequest.newBuilder()
        .setTransaction(transaction)
        .setTxId(txId)
        .setSighash(ByteString.copyFrom(sighash))
        .setValueBalance(valueBalance)
        .build();
    return blockingStub.checkZksnarkProof(request).getCode() == Code.SUCCESS;
  }
}
