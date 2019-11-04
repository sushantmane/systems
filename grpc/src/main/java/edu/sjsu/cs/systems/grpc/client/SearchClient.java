package edu.sjsu.cs.systems.grpc.client;

import edu.sjsu.cs.systems.grpc.SearchRequest;
import edu.sjsu.cs.systems.grpc.SearchResponse;
import edu.sjsu.cs.systems.grpc.SearchServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class SearchClient {

    private ManagedChannel channel;
    private SearchServiceGrpc.SearchServiceBlockingStub syncStub;

    public SearchClient() {
        ManagedChannelBuilder mcb = ManagedChannelBuilder.forTarget("localhost:9999")
                .usePlaintext();
        channel = mcb.build();
        syncStub = SearchServiceGrpc.newBlockingStub(channel);
    }

    public void search(String query) {
        SearchRequest request = SearchRequest.newBuilder()
                .setQuery(query)
                .setKResults(5)
                .build();
        SearchResponse response = syncStub.search(request);
        for (SearchResponse.Result result : response.getResultsList()) {
            System.out.println(result.getDocID() + " " + result.getScore());
        }
    }

    public static void main(String[] args) {
        SearchClient client = new SearchClient();
        client.search("how search works?");
    }
}
