package edu.sjsu.cs.systems.netty.server;

import edu.sjsu.cs.systems.netty.common.RequestData;
import edu.sjsu.cs.systems.netty.common.ResponseData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class SimpleProcessingHandler extends ChannelInboundHandlerAdapter {

    private ByteBuf buf;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        System.out.println("Added a handler...");
        buf = ctx.alloc().buffer(4);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        System.out.println("Removing handler");
        buf.release();
        buf = null;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
      RequestData requestData = (RequestData) msg;
      ResponseData responseData = new ResponseData();
      responseData.setId((long) requestData.getId() * requestData.getId());
      ChannelFuture future = ctx.writeAndFlush(responseData);
      future.addListener(ChannelFutureListener.CLOSE);
      System.out.println(requestData);
    }
}
