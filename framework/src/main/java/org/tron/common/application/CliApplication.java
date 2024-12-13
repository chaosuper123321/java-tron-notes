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

//提供一个命令行应用程序的框架。
public class CliApplication implements Application {
  //设置选项。
  @Override
  public void setOptions(Args args) {

  }

  //初始化。
  @Override
  public void init(CommonParameter parameter) {

  }

  //初始化服务。
  @Override
  public void initServices(CommonParameter parameter) {

  }

  //启动应用程序。
  @Override
  public void startup() {

  }

  //关闭应用程序。
  @Override
  public void shutdown() {

  }

  //启动服务。
  @Override
  public void startServices() {

  }

  //关闭服务。
  @Override
  public void shutdownServices() {

  }

  //添加服务。
  @Override
  public void addService(Service service) {

  }

  //获取数据库管理器。
  @Override
  public Manager getDbManager() {
    return null;
  }

  //获取链基本管理器。
  @Override
  public ChainBaseManager getChainBaseManager() {
    return null;
  }

}
