package com.flamexander.netty.servers.unlimited_reader;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class UnlimitedHandler extends ChannelInboundHandlerAdapter {
    private ByteBuf accumulator;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBufAllocator allocator = ctx.alloc();
        accumulator = allocator.directBuffer(1024 * 1024 * 1, 5 * 1024 * 1024);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf input = (ByteBuf) msg;
        accumulator.writeBytes(input);
        input.release();
        // ...
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("1.txt", true))) {
            while (accumulator.readableBytes() > 0) {
                out.write(accumulator.readByte());
            }
            accumulator.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
