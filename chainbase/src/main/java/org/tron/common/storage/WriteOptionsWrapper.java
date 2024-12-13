package org.tron.common.storage;

/*
操作顺序：首先，通过getInstance()方法初始化WriteOptionsWrapper实例，然后可以通过调用sync()方法来配置写入选项的同步行为。

性能方面：使用sync()方法配置写入操作的同步或异步执行可能会影响性能。同步写入确保数据的一致性和可靠性，但可能会降低写入速度。异步写入可能提高性能，但增加了数据丢失的风险。

可重用性：WriteOptionsWrapper设计为可重用的，可以轻松适应不同的数据库写入需求，无需对每个数据库进行单独的配置。

使用：此模块可用于任何需要对RocksDB或LevelDB进行写入操作的应用程序。通过提供统一的写入选项配置接口，简化了数据库操作的复杂性。
 */

/**
 * 为了提供一个统一的接口，用于配置和管理不同数据库（如RocksDB和LevelDB）的写入选项。
 * 通过这种方式，可以轻松切换数据库而无需对写入选项进行大量更改。
 */
public class WriteOptionsWrapper {

  public org.rocksdb.WriteOptions rocks = null;
  public org.iq80.leveldb.WriteOptions level = null;

  private WriteOptionsWrapper() {

  }

  //这是一个静态方法，用于创建WriteOptionsWrapper的实例。
  //它初始化了RocksDB和LevelDB的写入选项。
  public static WriteOptionsWrapper getInstance() {
    WriteOptionsWrapper wrapper = new WriteOptionsWrapper();
    wrapper.level = new org.iq80.leveldb.WriteOptions();
    wrapper.rocks = new org.rocksdb.WriteOptions();

    return wrapper;
  }


  //用于设置RocksDB和LevelDB的同步写入选项。
  //如果参数为true，则写入操作将同步到磁盘；
  //如果为false，则可能不会立即同步。
  public WriteOptionsWrapper sync(boolean bool) {
    this.level.sync(bool);
    this.rocks.setSync(bool);
    return this;
  }
}
