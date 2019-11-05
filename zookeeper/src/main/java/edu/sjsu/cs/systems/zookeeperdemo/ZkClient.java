package edu.sjsu.cs.systems.zookeeperdemo;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ZkClient implements Watcher {

    private ZooKeeper zk;

    private ZkClient(String target, int to) throws IOException {
        zk = new ZooKeeper(target, to, this);
    }

    @Override
    public void process(WatchedEvent event) {
    }

    public void createNode() throws KeeperException, InterruptedException {
        String data = "Baymax personal assistance";
        CreateMode mode = CreateMode.PERSISTENT;
        Id id = new Id("digest", "a");
        ACL acl = new ACL(ZooDefs.Perms.ALL, id);
        List<ACL> acls = Collections.singletonList(acl);
        zk.create("/baymax", data.getBytes(), acls, mode);
        zk.wait();
    }

    public void getNode() throws KeeperException, InterruptedException {
        byte[] data = new byte[4 * 1024];
        Stat stat = new Stat();
        zk.getData("/sushant", false, stat);
        System.out.println("*** STAT ****");
        System.out.println(stat.toString());

    }

    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        String host = "192.168.56.111:9999";
        int to = 120000;
        ZkClient zkc = new ZkClient(host, to);
        zkc.createNode();
//        zkc.getNode();
    }
}
