package edu.sjsu.cs.systems.rocksdbapp;

import org.rocksdb.FlushOptions;
import org.rocksdb.Options;
import org.rocksdb.ReadOptions;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
import org.rocksdb.WriteOptions;

public class RocksDbApp {

    static {
        // a static method that loads the RocksDB C++ library.
        RocksDB.loadLibrary();
    }

    void run() {
        String dbFile = "/tmp/testDB";
        RocksDB db = null;
        Options options = new Options();
        options.setCreateIfMissing(true);

        // open rocksdb
        try {
            db = RocksDB.open(options, dbFile);
        } catch (RocksDBException e) {
            e.printStackTrace();
        }
        String key = "register-x";
        byte[] keyB = key.getBytes();
        String val = "value-of-register-x";
        byte[] valB = val.getBytes();

        WriteOptions writeOptions = new WriteOptions();
        writeOptions.sync();
        // store key-value
        try {
            db.put(writeOptions, keyB, valB);
        } catch (RocksDBException e) {
            e.printStackTrace();
        }

        FlushOptions flushOptions = new FlushOptions();
        flushOptions.allowWriteStall();
        try {
            db.flush(flushOptions);
        } catch (RocksDBException e) {
            e.printStackTrace();
        }

        ReadOptions readOptions = new ReadOptions();
        readOptions.setPinData(true);
        // fetch value for given key
        try {
            valB = db.get(readOptions, keyB);
        } catch (RocksDBException e) {
            e.printStackTrace();
        }
        val = new String(valB);
        System.out.println(val);

        // delete key
        try {
            db.delete(keyB);
        } catch (RocksDBException e) {
            e.printStackTrace();
        }

        // try reading key after deleting it
        try {
            valB = db.get(keyB);
        } catch (RocksDBException e) {
            e.printStackTrace();
        }

        if (valB == null) {
            System.out.println("Key deleted!");
        }

        db.close();
    }

    public static void main(String[] args) throws RocksDBException {
        RocksDbApp app = new RocksDbApp();
        app.run();
    }
}
