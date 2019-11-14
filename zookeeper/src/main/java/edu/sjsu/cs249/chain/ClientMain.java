package edu.sjsu.cs249.chain;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class ClientMain implements Watcher {

    ZooKeeper zk;
    boolean conn = false;


    public ClientMain() throws IOException, InterruptedException, KeeperException {
        zk = new ZooKeeper("192.168.56.111:9999", 3000, this);
        while (!conn) {
            Thread.sleep(10);
        }
        System.out.println(zk.getSessionId());
        zk.create("/anket/" + zk.getSessionId(), "199.122.23.45:8765".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("event: " + watchedEvent.getState());

        if ("SyncConnected".equals(watchedEvent.getState().toString())) {
            conn = true;
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ClientMain main = new ClientMain();


//        ManagedChannel channel = ManagedChannelBuilder.forTarget("127.0.0.1:9998").usePlaintext().build();
//        TailChainReplicaGrpc.TailChainReplicaBlockingStub stub = TailChainReplicaGrpc.newBlockingStub(channel);
//        GetRequest request = GetRequest.newBuilder().setKey("sjsu").build();
//        GetResponse response = stub.get(request);
//        System.out.println(response.getRc());
//        System.out.println(response.getValue());
    }
}
