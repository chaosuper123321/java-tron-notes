package org.tron.common.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.tron.api.DatabaseGrpc;
import org.tron.api.GrpcAPI.EmptyMessage;
import org.tron.api.GrpcAPI.NumberMessage;
import org.tron.protos.Protocol.Block;
import org.tron.protos.Protocol.DynamicProperties;

public class DatabaseGrpcClient {

  private final ManagedChannel channel;
  private final DatabaseGrpc.DatabaseBlockingStub databaseBlockingStub;

  //构造函数用于初始化与数据库的连接。
  public DatabaseGrpcClient(String host, int port) {
    channel = ManagedChannelBuilder.forAddress(host, port)
        .usePlaintext()
        .build();
    databaseBlockingStub = DatabaseGrpc.newBlockingStub(channel);
  }

  public DatabaseGrpcClient(String host) {
    channel = ManagedChannelBuilder.forTarget(host)
        .usePlaintext()
        .build();
    databaseBlockingStub = DatabaseGrpc.newBlockingStub(channel);
  }

  //根据给定的区块号获取相应的区块数据。
  public Block getBlock(long blockNum) {
    if (blockNum < 0) {
      return databaseBlockingStub.getNowBlock(EmptyMessage.newBuilder().build());
    }
    NumberMessage.Builder builder = NumberMessage.newBuilder();
    builder.setNum(blockNum);
    return databaseBlockingStub.getBlockByNum(builder.build());
  }

  //关闭与数据库的连接。
  public void shutdown() {
    channel.shutdown();
  }

  //获取动态属性数据。
  public DynamicProperties getDynamicProperties() {
    return databaseBlockingStub.getDynamicProperties(EmptyMessage.newBuilder().build());
  }
}
