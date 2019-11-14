package edu.sjsu.cs249.chain;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;

public class ReplicaMain extends TailChainReplicaGrpc.TailChainReplicaImplBase {

    private Server server;

    public ReplicaMain() {
        server = ServerBuilder.forPort(9998).addService(this).build();
    }

    public void start() throws IOException, InterruptedException {
        server.start();
        System.out.println("Server started...");
        server.awaitTermination();
    }

    @Override
    public void get(GetRequest request, StreamObserver<GetResponse> streamObserver) {
        String key = request.getKey();
        GetResponse.Builder builder = GetResponse.newBuilder().setRc(0).setValue(1234);
        System.out.println("Received request for key : " + key);
        streamObserver.onNext(builder.build());
        streamObserver.onCompleted();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ReplicaMain main = new ReplicaMain();
        main.start();
    }
}
