package edu.sjsu.cs.systems.netty.client;

import edu.sjsu.cs.systems.netty.common.ResponseData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class ResponseDataDecoder extends ReplayingDecoder<ResponseData> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) throws Exception {
        ResponseData responseData = new ResponseData();
        responseData.setId(byteBuf.readLong());
        list.add(responseData);
    }
}
