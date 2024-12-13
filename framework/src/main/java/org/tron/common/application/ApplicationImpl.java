package org.tron.common.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tron.common.parameter.CommonParameter;
import org.tron.core.ChainBaseManager;
import org.tron.core.config.args.Args;
import org.tron.core.consensus.ConsensusService;
import org.tron.core.db.Manager;
import org.tron.core.metrics.MetricsUtil;
import org.tron.core.net.TronNetService;

@Slf4j(topic = "app")
@Component
public class ApplicationImpl implements Application {

  private ServiceContainer services;

  @Autowired
  private TronNetService tronNetService;

  @Autowired
  private Manager dbManager;

  @Autowired
  private ChainBaseManager chainBaseManager;

  @Autowired
  private ConsensusService consensusService;

  //设置选项，但当前未使用。
  @Override
  public void setOptions(Args args) {
    // not used
  }

  //初始化应用程序，创建服务容器。
  @Override
  @Autowired
  public void init(CommonParameter parameter) {
    services = new ServiceContainer();
  }

  //添加服务到服务容器。
  @Override
  public void addService(Service service) {
    services.add(service);
  }

  //初始化所有服务。
  @Override
  public void initServices(CommonParameter parameter) {
    services.init(parameter);
  }

  //启动应用程序，启动网络服务和共识服务等。
  @Override
  public void startup() {
    if ((!Args.getInstance().isSolidityNode()) && (!Args.getInstance().isP2pDisable())) {
      tronNetService.start();
    }
    consensusService.start();
    MetricsUtil.init();
    this.initServices(Args.getInstance());
    this.startServices();
  }

  //关闭应用程序，关闭所有服务。
  @Override
  public void shutdown() {
    this.shutdownServices();
    consensusService.stop();
    if (!Args.getInstance().isSolidityNode() && (!Args.getInstance().p2pDisable)) {
      tronNetService.close();
    }
    dbManager.close();
  }

  //启动所有服务。
  @Override
  public void startServices() {
    services.start();
  }

  //阻塞直到所有服务关闭。
  @Override
  public void blockUntilShutdown() {
    services.blockUntilShutdown();
  }

  //关闭所有服务。
  @Override
  public void shutdownServices() {
    services.stop();
  }

  //获取数据库管理器。
  @Override
  public Manager getDbManager() {
    return dbManager;
  }

  //获取链基础管理器。
  @Override
  public ChainBaseManager getChainBaseManager() {
    return chainBaseManager;
  }

}
