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
import io.grpc.Server;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

/**
 * 调用start()方法启动RPC服务器。
 * 调用blockUntilShutdown()方法阻塞直到服务器关闭。
 * 调用stop()方法停止RPC服务器。
 */
@Slf4j(topic = "rpc")
//实现RPC服务的功能。
public abstract class RpcService implements Service {

  protected Server apiServer;
  protected int port;

  //阻塞直到服务器关闭。
  @Override
  public void blockUntilShutdown() {
    if (apiServer != null) {
      try {
        apiServer.awaitTermination();
      } catch (InterruptedException e) {
        logger.warn("{}", e.getMessage());
        Thread.currentThread().interrupt();
      }
    }
  }

  //启动RPC服务器。
  @Override
  public void start() {
    if (apiServer != null) {
      try {
        apiServer.start();
        logger.info("{} started, listening on {}", this.getClass().getSimpleName(), port);
      } catch (IOException e) {
        logger.error("{}", this.getClass().getSimpleName(), e);
      }
    }
  }

  //停止RPC服务器。
  @Override
  public void stop() {
    if (apiServer != null) {
      logger.info("{} shutdown...", this.getClass().getSimpleName());
      try {
        apiServer.shutdown().awaitTermination(5, TimeUnit.SECONDS);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        logger.warn("{}", this.getClass().getSimpleName(), e);
      }
      logger.info("{} shutdown complete", this.getClass().getSimpleName());
    }
  }

  //比较两个RpcService对象是否相等。
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RpcService that = (RpcService) o;
    return port == that.port;
  }

  //计算RpcService对象的哈希值。
  @Override
  public int hashCode() {
    return Objects.hashCode(getClass().getSimpleName(), port);
  }

}
