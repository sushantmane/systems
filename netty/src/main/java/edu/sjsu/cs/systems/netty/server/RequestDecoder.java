package edu.sjsu.cs.systems.netty.server;

import edu.sjsu.cs.systems.netty.common.RequestData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class RequestDecoder extends ReplayingDecoder<RequestData> {

    private final Charset charset = StandardCharsets.UTF_8;


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) throws Exception {
        RequestData requestData = new RequestData();
        requestData.setId(byteBuf.readInt());
        int strLen = byteBuf.readInt();
        requestData.setName(byteBuf.readCharSequence(strLen, charset).toString());
        list.add(requestData);
    }
}
