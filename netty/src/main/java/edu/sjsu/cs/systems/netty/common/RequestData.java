package edu.sjsu.cs.systems.netty.common;

public class RequestData {

    private String name;
    private int id;

    public RequestData() {
    }

    public RequestData setId(int id) {
        this.id = id;
        return this;
    }

    public int getId() {
        return id;
    }

    public RequestData setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }


}
