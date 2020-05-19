package com.geekbrains.brains.cloud.server;

import com.geekbrains.brains.cloud.common.ProtoHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MainHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead( ChannelHandlerContext ctx, Object msg ) {
        ByteBuf buf = ((ByteBuf) msg);
        ctx.pipeline().addLast(new ProtoHandler());
        ctx.fireChannelRead(buf);
    }

    @Override
    public void exceptionCaught( ChannelHandlerContext ctx, Throwable cause ) {
        cause.printStackTrace();
        ctx.close();
    }
}
