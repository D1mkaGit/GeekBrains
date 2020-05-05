package com.flamexander.netty.servers.file_transfer.caching_error;

import com.flamexander.netty.servers.file_transfer.chunked_file.client.Network;
import com.flamexander.netty.servers.serialization.MyMessage;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

public class MainHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FileRequest) {
            new Thread(() -> {
                try {
                    File file = new File("2.txt");
                    int bufSize = 1024 * 1024 * 10;
                    int partsCount = new Long(file.length() / bufSize).intValue();
                    if (file.length() % bufSize != 0) {
                        partsCount++;
                    }
                    FileMessage fmOut = new FileMessage("file.dat", -1, partsCount, new byte[bufSize]);
                    FileInputStream in = new FileInputStream(file);
                    for (int i = 0; i < partsCount; i++) {
                        int readedBytes = in.read(fmOut.data);
                        fmOut.partNumber = i + 1;
                        if (readedBytes < bufSize) {
                            fmOut.data = Arrays.copyOfRange(fmOut.data, 0, readedBytes);
                        }
                        ctx.writeAndFlush(fmOut);
                        Thread.sleep(100);
                        System.out.println("Отправлена часть #" + (i + 1));
                    }
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
