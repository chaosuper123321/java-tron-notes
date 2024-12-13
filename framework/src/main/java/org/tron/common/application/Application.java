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

import org.tron.common.parameter.CommonParameter;
import org.tron.core.ChainBaseManager;
import org.tron.core.config.args.Args;
import org.tron.core.db.Manager;

public interface Application {

  //设置选项参数。
  void setOptions(Args args);

  //初始化应用程序。
  void init(CommonParameter parameter);

  //初始化应用程序服务。
  void initServices(CommonParameter parameter);

  //启动应用程序。
  void startup();

  //关闭应用程序。
  void shutdown();

  //启动服务。
  void startServices();

  // 阻止直到关闭。
  default void blockUntilShutdown() {
  }

  //关闭服务。
  void shutdownServices();

  //添加服务。
  void addService(Service service);

  //获取数据库管理器。
  Manager getDbManager();

  //获取链基础管理器。
  ChainBaseManager getChainBaseManager();

}
