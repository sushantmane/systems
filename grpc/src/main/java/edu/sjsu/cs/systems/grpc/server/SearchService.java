package edu.sjsu.cs.systems.grpc.server;

import edu.sjsu.cs.systems.grpc.SearchRequest;
import edu.sjsu.cs.systems.grpc.SearchResponse;
import edu.sjsu.cs.systems.grpc.SearchServiceGrpc;
import io.grpc.stub.StreamObserver;

public class SearchService extends SearchServiceGrpc.SearchServiceImplBase {

    private int score = 10;

    public SearchService() {
        System.out.println(this);
    }

    @Override
    public void search(SearchRequest request, StreamObserver<SearchResponse> observer) {
        System.out.println(this);
        SearchResponse response = SearchResponse.newBuilder()
                .addResults(
                        SearchResponse.Result.newBuilder().setDocID(1).setScore(score--).build())
                .addResults(
                        SearchResponse.Result.newBuilder().setDocID(2).setScore(score--).build())
                .build();
        observer.onNext(response);
        observer.onCompleted();
    }
}
