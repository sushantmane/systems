package edu.sjsu.cs.systems.grpc.server;

import edu.sjsu.cs.systems.grpc.IrEngine;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class IrengServer {

    private Server server;
    private int port;

    public IrengServer(int port) {
        this.port = port;
        ServerBuilder sb = ServerBuilder.forPort(port);
        sb.addService(new SearchService());
        server = sb.build();
    }

    public void start() throws IOException, InterruptedException {
        server.start();
        server.awaitTermination();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        IrengServer irengServer = new IrengServer(9999);
        irengServer.start();
    }
}
