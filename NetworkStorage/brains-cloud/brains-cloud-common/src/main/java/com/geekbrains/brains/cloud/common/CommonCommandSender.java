package com.geekbrains.brains.cloud.common;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class CommonCommandSender {
    public static void sendCommand( Channel channel, String s, byte cmdSignalByte ) {
        byte[] cmdNameBytes = (s).getBytes(StandardCharsets.UTF_8);
        ByteBuf buf = ByteBufAllocator.DEFAULT.directBuffer(1 + 4 + cmdNameBytes.length);
        buf.writeByte(cmdSignalByte);
        buf.writeInt(cmdNameBytes.length);
        buf.writeBytes(cmdNameBytes);
        channel.writeAndFlush(buf);
    }

    public static void sendFileListCmd( ChannelHandlerContext ctx, String storageFolderName ) throws IOException {
        String filesList = Files.list(Paths.get(storageFolderName))
                .filter(p -> !Files.isDirectory(p))
                .map(p -> p.getFileName().toString())
                .collect(Collectors.joining("|"));

        sendCommand(ctx.channel(), "/filesList " + filesList, CloudBoxCommandsList.CMD_SIGNAL_BYTE);
    }
}

