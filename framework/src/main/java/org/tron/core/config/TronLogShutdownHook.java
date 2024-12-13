package org.tron.core.config;

import ch.qos.logback.core.hook.ShutdownHookBase;
import ch.qos.logback.core.util.Duration;
import org.tron.program.FullNode;

/**
 * 实现日志记录的关闭钩子功能。
 */
public class TronLogShutdownHook extends ShutdownHookBase {

  //默认的关闭延迟检查单位。
  private static final Duration CHECK_SHUTDOWN_DELAY = Duration.buildByMilliseconds(100);

  //在关闭之前的检查次数，默认为600次。
  private final long  check_times = 60 * 1000 / CHECK_SHUTDOWN_DELAY.getMilliseconds();

  //控制是否执行关闭钩子的标志。
  public static volatile boolean shutDown = true;

  public TronLogShutdownHook() {
  }

  //运行方法，用于执行关闭钩子的逻辑。
  @Override
  public void run() {
    try {
      for (int i = 0; i < check_times; i++) {
        if (shutDown) {
          break;
        }
        addInfo("Sleeping for " + CHECK_SHUTDOWN_DELAY);
        Thread.sleep(CHECK_SHUTDOWN_DELAY.getMilliseconds());
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      addInfo("TronLogShutdownHook run error :" + e.getMessage());
    }
    super.stop();
  }

}
