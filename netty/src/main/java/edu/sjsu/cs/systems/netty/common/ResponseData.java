package edu.sjsu.cs.systems.netty.common;

public class ResponseData {

    private long id;

    public ResponseData setId(long id) {
        this.id = id;
        return this;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "id:" + id;
    }
}
