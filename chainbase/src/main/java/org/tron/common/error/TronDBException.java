package org.tron.common.error;

/**
 * 该模块的目的是定义一个专门用于处理Tron数据库操作中可能出现的异常情况的异常类。它扩展了Java的RuntimeException，使得使用该异常类可以在运行时捕获数据库操作中的错误，并允许开发者进行相应的错误处理。
 *
 * 核心与辅助操作：所有构造函数都是核心操作，因为它们直接支持创建异常实例的能力。没有明显的辅助操作。
 *
 * 操作序列：通常，当数据库操作失败时，会创建一个TronDBException实例，可能包含错误消息和/或原始异常，然后抛出该异常以供调用者捕获和处理。
 *
 * 性能方面：异常处理通常与性能考虑相关，因为异常的创建和抛出可能是资源密集型的。然而，TronDBException作为一个主要用于错误处理的类，其性能影响通常被视为管理错误状态的必要开销。
 *
 * 可重用性：由于TronDBException是一个相对通用的异常类，它可以在不同的数据库操作场景中重用，特别是在处理与Tron数据库相关的操作时。
 *
 * 使用：在Tron数据库操作中，当遇到异常情况（如连接失败、查询错误等）时，可以抛出TronDBException，并可选地包含错误消息和/或原始异常，以提供更多的上下文信息。
 *
 */
public class TronDBException extends RuntimeException {
    //无参数构造函数，创建一个基本的异常实例。
    public TronDBException() {
    }

    //接受一个字符串参数的构造函数，用于创建包含错误消息的异常实例。
    public TronDBException(String s) {
    super(s);
  }

    //接受一个字符串和一个Throwable作为参数的构造函数，用于创建包含错误消息和一个原始异常的异常实例。
    public TronDBException(String s, Throwable throwable) {
    super(s, throwable);
  }

    //接受一个Throwable作为参数的构造函数，用于创建一个基于原始异常的异常实例。
    public TronDBException(Throwable throwable) {
    super(throwable);
  }
}
