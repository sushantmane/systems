## RocksDB
RocksDB Homepage:
> The RocksDB library provides a persistent key value store. Keys and values are arbitrary byte arrays. The keys are ordered within the key value store according to a user-specified comparator function.

### Samples
- https://github.com/facebook/rocksdb/tree/master/java/samples/src/main/java

### Basic Usage
```java

// a static method that loads the RocksDB C++ library.
RocksDB.loadLibrary();

// opening a database
RocksDB db = RocksDB.open(options, dbFile);

// store a key-value pair
db.put(keyString.getBytes(), valueString.getBytes());

// fetch value of a key
byte[] value = db.get(keyString.getBytes());

// delete k-v entry
db.delete(keyString.getBytes())

// close db
db.close();
```
