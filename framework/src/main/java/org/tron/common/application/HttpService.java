/*
 * java-tron is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * java-tron is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.tron.common.application;

import com.google.common.base.Objects;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;

//提供HTTP服务的功能。
@Slf4j(topic = "rpc")
public abstract class HttpService implements Service {
  //用于存储HTTP服务器实例。
  protected Server apiServer;
  //存储端口号。
  protected int port;

  //阻塞直到服务器关闭。
  @Override
  public void blockUntilShutdown() {
    if (apiServer != null) {
      try {
        apiServer.join();
      } catch (InterruptedException e) {
        logger.warn("{}", e.getMessage());
        Thread.currentThread().interrupt();
      }
    }
  }

  //启动HTTP服务器。
  @Override
  public void start() {
    if (apiServer != null) {
      try {
        apiServer.start();
        logger.info("{} started, listening on {}", this.getClass().getSimpleName(), port);
      } catch (Exception e) {
        logger.error("{}", this.getClass().getSimpleName(), e);
      }
    }
  }

  //停止HTTP服务器。
  @Override
  public void stop() {
    if (apiServer != null) {
      logger.info("{} shutdown...", this.getClass().getSimpleName());
      try {
        apiServer.stop();
      } catch (Exception e) {
        logger.warn("{}", this.getClass().getSimpleName(), e);
      }
      logger.info("{} shutdown complete", this.getClass().getSimpleName());
    }
  }

  //比较端口号是否相同。
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HttpService that = (HttpService) o;
    return port == that.port;
  }

  //返回类名和端口号的哈希值。
  @Override
  public int hashCode() {
    return Objects.hashCode(getClass().getSimpleName(), port);
  }
}
