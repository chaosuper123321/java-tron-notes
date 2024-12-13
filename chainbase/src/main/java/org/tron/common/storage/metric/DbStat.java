package org.tron.common.storage.metric;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.tron.common.prometheus.MetricKeys;
import org.tron.common.prometheus.Metrics;


/**
 * 操作序列：首先调用statProperty()方法，该方法内部会调用getStats()获取数据库统计信息，然后解析这些信息，并通过Metrics.gaugeSet设置相应的Prometheus指标。
 *
 * 性能方面：需要注意的性能考虑包括统计信息收集的效率以及对数据库性能的影响。
 *
 * 可重用性：该模块设计为抽象类，通过抽象方法的方式要求子类实现具体的数据库统计信息获取，这种设计提高了模块的可重用性，可以适应不同的数据库引擎。
 *
 * 使用方法：要使用该模块，需要创建一个继承自DbStat的子类，并实现getStats()、getEngine()和getName()这三个抽象方法。
 */

//监控和统计数据库状态，包括文件数量和数据库大小等信息。
@Slf4j(topic = "metrics")
public abstract class DbStat {

  //该方法负责收集数据库统计信息，并通过Prometheus指标进行记录。
  protected void statProperty() {
    try {
      getStats().forEach(stat -> {
        String[] tmp = stat.trim().replaceAll(" +", ",").split(",");
        String level = tmp[0];
        double files = Double.parseDouble(tmp[1]);
        double size = Double.parseDouble(tmp[2]) * 1048576.0;
        Metrics.gaugeSet(MetricKeys.Gauge.DB_SST_LEVEL, files, getEngine(), getName(), level);
        Metrics.gaugeSet(MetricKeys.Gauge.DB_SIZE_BYTES, size, getEngine(), getName(), level);
        logger.info("DB {}, level:{},files:{},size:{} M",
            getName(), level, files, size / 1048576.0);
      });
    } catch (Exception e) {
      logger.warn("DB {} stats error", getName(), e);
    }
  }

  //抽象方法，要求子类实现，用于获取数据库的统计信息。
  public abstract List<String> getStats() throws Exception;

  //抽象方法，要求子类实现，用于获取数据库引擎名称。
  public abstract String getEngine();

  //抽象方法，要求子类实现，用于获取数据库的名称。
  public abstract String getName();

}
