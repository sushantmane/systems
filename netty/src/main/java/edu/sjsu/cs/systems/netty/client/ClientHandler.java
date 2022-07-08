package edu.sjsu.cs.systems.netty.client;

import edu.sjsu.cs.systems.netty.common.RequestData;
import edu.sjsu.cs.systems.netty.common.ResponseData;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        RequestData requestData = new RequestData();
        requestData.setId(42);
        requestData.setName("Baymax - The Big Hero");
        ChannelFuture future = ctx.writeAndFlush(requestData);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println((ResponseData)msg);
        ctx.close();
    }
}
