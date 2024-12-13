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

//定义一个服务接口，用于初始化、启动、停止服务，并在必要时阻塞直到服务关闭。

/**
 * 调用init()或init(CommonParameter parameter)方法初始化服务。
 * 调用start()方法启动服务。
 * 在需要时调用stop()方法停止服务。
 * 调用blockUntilShutdown()方法阻塞程序直到服务关闭。
 */
public interface Service {

  //用于初始化服务。
  void init();

  //用指定参数初始化服务。
  void init(CommonParameter parameter);

  //启动服务，必须在调用init(CommonParameter parameter)方法后调用。
  void start();

  //停止服务。
  void stop();

  //在服务关闭之前阻塞程序执行。
  void blockUntilShutdown();
}
