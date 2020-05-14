package com.geekbrains.brains.cloud.server;

import com.geekbrains.brains.cloud.common.FileMessage;
import com.geekbrains.brains.cloud.common.FileRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class MainHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead( ChannelHandlerContext ctx, Object msg ) throws Exception {
        if (msg instanceof FileRequest) {
            FileRequest fr = (FileRequest) msg;
            if (Files.exists(Paths.get("server_storage/" + fr.getFilename()))) {
                FileMessage fm = new FileMessage(Paths.get("server_storage/" + fr.getFilename()));
                ctx.writeAndFlush(fm);
            }
        }
        if (msg instanceof FileMessage) {
            FileMessage fm = (FileMessage) msg;
            Files.write(Paths.get("server_storage/" + fm.getFilename()), fm.getData(), StandardOpenOption.CREATE);

        }
    }

    @Override
    public void exceptionCaught( ChannelHandlerContext ctx, Throwable cause ) {
        cause.printStackTrace();
        ctx.close();
    }
}
