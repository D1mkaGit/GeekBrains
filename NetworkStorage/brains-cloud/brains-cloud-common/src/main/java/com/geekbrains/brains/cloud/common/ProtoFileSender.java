package com.geekbrains.brains.cloud.common;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class ProtoFileSender extends AbstractMessage {
    public static void sendFile( byte byteNumber, Path path, Channel channel, ChannelFutureListener finishListener ) throws IOException {
        FileRegion region = new DefaultFileRegion(path.toFile(), 0, Files.size(path));
        byte[] filenameBytes = path.getFileName().toString().getBytes(StandardCharsets.UTF_8);
        // 1 + 4 + filenameBytes.length +8 -> SIGNAL_BITE + FILENAME_LENGTH(int) + FILENAME + FILE_LENGTH(long)
        ByteBuf buf = ByteBufAllocator.DEFAULT.directBuffer(1 + 4 + filenameBytes.length + 8);
        buf.writeByte(byteNumber);
        buf.writeInt(filenameBytes.length);
        buf.writeBytes(filenameBytes);
        buf.writeLong(Files.size(path));
        channel.writeAndFlush(buf);

        ChannelFuture transferOperationFuture = channel.writeAndFlush(region);
        if (finishListener != null) {
            transferOperationFuture.addListener(finishListener);
        }
    }

    public static void sendRequest( String reqFileName, Channel channel ) {
        byte[] filenameBytes = reqFileName.getBytes(StandardCharsets.UTF_8);
        // 1 + 4 + filenameBytes.length -> SIGNAL_BITE + FILENAME_LENGTH(int) + FILENAME
        ByteBuf buf = ByteBufAllocator.DEFAULT.directBuffer(1 + 4 + filenameBytes.length);
        buf.writeByte((byte) 24);
        buf.writeInt(filenameBytes.length);
        buf.writeBytes(filenameBytes);
        channel.writeAndFlush(buf);
    }
}
