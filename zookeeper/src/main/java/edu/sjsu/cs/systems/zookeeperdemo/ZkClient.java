package edu.sjsu.cs.systems.zookeeperdemo;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ZkClient implements Watcher {

    private static final Logger LOG = LoggerFactory.getLogger(ZkClient.class);

    private ZooKeeper zk;

    private ZkClient(String target, int to) throws IOException {
        zk = new ZooKeeper(target, to, this);
    }

    @Override
    public void process(WatchedEvent event) {
    }

    public void createNode(String node, String data) throws KeeperException, InterruptedException {
        CreateMode mode = CreateMode.PERSISTENT;
        zk.create(node, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, mode);
    }

    public void getNode(String node) throws KeeperException, InterruptedException {
        Stat stat = new Stat();
        zk.getData(node, false, stat);
        System.out.println(stat.toString());
    }

    public void checkNode(String node) throws KeeperException, InterruptedException {
        Stat stat = zk.exists(node, false);
        System.out.println(stat);
    }

    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        String host = "192.168.56.111:9999";
        int to = 120000;
        ZkClient zkc = new ZkClient(host, to);
        String node = "/head-chain";
        String data = "Hello I'm a head!";
        zkc.createNode(node, data);
        zkc.getNode(node);
        zkc.checkNode(node);
    }
}
