/*
 * Copyright (c) [2016] [ <ether.camp> ]
 * This file is part of the ethereumJ library.
 *
 * The ethereumJ library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The ethereumJ library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with the ethereumJ library. If not, see <http://www.gnu.org/licenses/>.
 */
package org.tron.common.runtime;


/**
 * 该模块的目的是为了创建一个表示区块链上的调用或合约创建操作的数据结构。
 * 它封装了与这些操作相关的所有必要信息，如目标地址、传输数据、能量限制和价值。
 */

/**
 * 操作序列：首先通过构造函数初始化一个CallCreate实例，随后可以通过各种getter方法访问实例的具体信息。
 *
 * 性能方面：性能考虑可能包括构造函数和方法的执行效率，尤其是在处理大量的调用/创建操作时。内存使用效率也是一个考虑因素。
 *
 * 可重用性：该模块设计为一个简单且专一的数据结构，因此具有很高的可重用性，可以轻松集成到需要处理区块链调用或创建操作的任何系统中。
 *
 * 使用：在区块链系统中，当需要创建一个新的合约调用或合约创建操作时，可以使用此模块。它为这些操作提供了一个标准化的表示方式。
 */

public class CallCreate {

  private final byte[] data; //关联的数据。
  private final byte[] destination; //目标地址。
  private final byte[] energyLimit; //能量限制。
  private final byte[] value; //价值。


  public CallCreate(byte[] data, byte[] destination, byte[] energyLimit, byte[] value) {
    this.data = data;
    this.destination = destination;
    this.energyLimit = energyLimit;
    this.value = value;
  }

  //返回与调用或创建操作相关的数据。
  public byte[] getData() {
    return data;
  }

  //返回目标地址。
  public byte[] getDestination() {
    return destination;
  }

  //返回操作的能量限制。
  public byte[] getEnergyLimit() {
    return energyLimit;
  }

  //返回传输的价值。
  public byte[] getValue() {
    return value;
  }
}
