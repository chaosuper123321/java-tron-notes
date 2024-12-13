package org.tron.common.application;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.tron.core.config.TronLogShutdownHook;

public class TronApplicationContext extends AnnotationConfigApplicationContext {

  //默认构造函数。
  public TronApplicationContext() {
  }

  //使用给定的bean工厂构造函数。
  public TronApplicationContext(DefaultListableBeanFactory beanFactory) {
    super(beanFactory);
  }

  //使用给定的注释类构造函数。
  public TronApplicationContext(Class<?>... annotatedClasses) {
    super(annotatedClasses);
  }

  //使用给定的基本包构造函数。
  public TronApplicationContext(String... basePackages) {
    super(basePackages);
  }

  //关闭应用程序上下文，并执行关闭钩子。
  @Override
  public void doClose() {
    logger.info("******** start to close ********");
    Application appT = ApplicationFactory.create(this);
    appT.shutdown();
    super.doClose();
    logger.info("******** close end ********");
    TronLogShutdownHook.shutDown = true;
  }

  //注册关闭钩子。
  @Override
  public void registerShutdownHook() {
    super.registerShutdownHook();
    TronLogShutdownHook.shutDown = false;
  }
}
