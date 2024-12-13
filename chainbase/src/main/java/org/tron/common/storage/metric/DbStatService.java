package org.tron.common.storage.metric;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.tron.common.es.ExecutorServiceManager;
import org.tron.common.prometheus.Metrics;
import org.tron.core.db.common.DbSourceInter;
import org.tron.core.db2.common.DB;

/*
核心与辅助操作：
  核心操作包括注册数据库实例并定期执行统计任务。
  辅助操作包括执行器的创建和关闭。

操作序列：
  创建单线程计划执行服务。
  通过register方法注册数据库实例。
  如果指标被启用，以固定延迟执行统计任务。
  通过shutdown方法关闭执行服务。

性能方面：
  使用单线程执行器限制了并发执行，保证了统计任务的顺序执行，减少了对数据库的并发压力。
  固定延迟的执行策略有助于平衡统计任务对系统资源的占用。

可重用性：
  该模块设计为通用的数据库统计服务，可以通过注册不同的数据库实例来重用。

使用：
  在需要收集数据库统计信息的系统中，通过调用register方法注册数据库实例，然后根据需要调用shutdown来优雅地关闭服务。
 */

//定期收集数据库的统计信息，并且在启用了指标（Metrics）的情况下，按照预定的时间间隔执行统计任务。
@Slf4j(topic = "metrics")
@Component
public class DbStatService {

  private final String esName = "db-stats";  //用于标识执行统计任务的执行器服务名称。
  private final ScheduledExecutorService statExecutor  =   //一个计划执行服务，用于安排和执行数据库统计任务。
      ExecutorServiceManager.newSingleThreadScheduledExecutor(esName);

  //用于注册需要进行统计的数据库实例。如果指标（Metrics）被启用，它会安排数据库统计任务以固定延迟执行。
  public  void register(DB<byte[], byte[]> db) {
    if (Metrics.enabled()) {
      statExecutor.scheduleWithFixedDelay(db::stat, 0, 6, TimeUnit.HOURS);
    }
  }

  //这是一个重载的方法，用于注册另一种类型的数据库实例进行统计。
  public  void register(DbSourceInter<byte[]> db) {
    if (Metrics.enabled()) {
      statExecutor.scheduleWithFixedDelay(db::stat, 0, 6, TimeUnit.HOURS);
    }
  }

  //用于关闭统计执行器，如果指标（Metrics）被启用，它会等待当前任务完成后关闭执行器。
  public void shutdown() {
    if (Metrics.enabled()) {
      ExecutorServiceManager.shutdownAndAwaitTermination(statExecutor, esName);
    }
  }
}
