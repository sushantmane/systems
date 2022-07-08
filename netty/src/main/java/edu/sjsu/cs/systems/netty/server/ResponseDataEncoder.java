package edu.sjsu.cs.systems.netty.server;

import edu.sjsu.cs.systems.netty.common.ResponseData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ResponseDataEncoder extends MessageToByteEncoder<ResponseData> {

    @Override
    protected void encode(ChannelHandlerContext ctx, ResponseData responseData, ByteBuf byteBuf) throws Exception {
        byteBuf.writeLong(responseData.getId());
    }
}
