package com.geekbrains.brains.cloud.common;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class ProtoFileSender extends AbstractMessage {
    public static final String SRV_TEMP_PATH = "server_storage/temp/";

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

    public static void sendReqForFilesList( Channel channel ) {
        ByteBuf buf = ByteBufAllocator.DEFAULT.directBuffer(1);
        buf.writeByte((byte) 22);
        channel.writeAndFlush(buf);
    }

    public static void sendFilesList( String source, Channel channel ) {
        String filesList;
        String tempFileName = SRV_TEMP_PATH + "temp.txt";
        if (!ProtoFileSender.SRV_TEMP_PATH.contains(source))
            tempFileName = source + "_storage/temp/temp.txt";
        try {
            filesList = Files.list(Paths.get(source))
                    .filter(p -> !Files.isDirectory(p))
                    .map(p -> p.getFileName().toString())
                    .collect(Collectors.joining("|"));
            Files.write(Paths.get(tempFileName), filesList.getBytes());
            String finalTempFileName = tempFileName;
            sendFile((byte) 23, Paths.get(tempFileName), channel, future -> {
                if (!future.isSuccess()) {
                    future.cause().printStackTrace();
                }
                if (future.isSuccess()) {
                    Files.deleteIfExists(Paths.get(finalTempFileName));
                    System.out.println("Список файлов успешно передан");
                }
            });
        } catch (IOException e) {
            System.out.println("Problems with sending file list for " + source + " directory");
            e.printStackTrace();
        }
    }
}
