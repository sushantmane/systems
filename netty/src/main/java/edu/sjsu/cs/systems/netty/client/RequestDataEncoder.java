package edu.sjsu.cs.systems.netty.client;

import edu.sjsu.cs.systems.netty.common.RequestData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.StandardCharsets;

public class RequestDataEncoder extends MessageToByteEncoder<RequestData> {

    @Override
    protected void encode(ChannelHandlerContext ctx, RequestData requestData, ByteBuf byteBuf) throws Exception {
        byteBuf.writeInt(requestData.getId());
        byteBuf.writeInt(requestData.getName().length());
        byteBuf.writeBytes(requestData.getName().getBytes(StandardCharsets.UTF_8));
    }
}
