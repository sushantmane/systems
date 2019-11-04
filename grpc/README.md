gRPC
====
Notes for future reference (content lifted from https://www.grpc.io)

### gRPC Usage

* define a service
* define methods that can be called remotely with their parameters and return types
* gRPC Server
  * implements service interface
  * runs a gRPC server to handle client calls
* gRPC client
  * has stub - provides access to methods on server


### gRPC Concepts
#### Protocol buffers
* Interface Description Language (IDL) and
* underlying message interchange format.


#### Service Definition
  -  define a service
  - specify methods that can be called remotely with their parameters and return types

```
message SearchRequest {
    string query = 1;
    int32 k_results = 2;
}

message SearchResponse {
    message Result {
        int32 docID = 1;
        float score = 2;
    }
    repeated Result results = 1;
}

service SearchService {
    rpc Search (SearchRequest) returns (SearchResponse);
}
```


##### Types of Services
###### 1. Unary RPCs
```
service SearchService {
    rpc Search (SearchRequest) returns (SearchResponse);
}
```

###### 2. Server Streaming RPCs
```
service SearchService {
    rpc Search (SearchRequest) returns (stream SearchResponse);
}
```

###### 3. Client Streaming RPCs
```
service SearchService {
    rpc Search (stream SearchRequest) returns (SearchResponse);
}
```

###### 4. Bidirectional Streaming RPCs
```
service SearchService {
    rpc Search (stream SearchRequest) returns (steam SearchResponse);
}
```

#### RPC Lifecycle
