package com.geekbrains.brains.cloud.server;


import com.geekbrains.brains.cloud.common.CloudBoxCommandsList;
import com.geekbrains.brains.cloud.common.CommandReceiver;
import com.geekbrains.brains.cloud.common.ProtoFileSender;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class ServerCommandReceiver extends CommandReceiver {
    @Override
    public void parseCommand( ChannelHandlerContext ctx, String cmd ) throws Exception {
        if (cmd.startsWith("/request ")) {
            String fileToClientName = cmd.split("\\s")[1];
            ProtoFileSender.sendFile(Paths.get("server_storage", fileToClientName), ctx.channel(), null);
        }

        if (cmd.startsWith("/list ")) {
            String filesList = Files.list(Paths.get("server_storage"))
                    .filter(p -> !Files.isDirectory(p))
                    .map(p -> p.getFileName().toString())
                    .collect(Collectors.joining("|"));

            byte[] cmdNameBytes = ("/filesList " + filesList).getBytes(StandardCharsets.UTF_8);
            ByteBuf buf = ByteBufAllocator.DEFAULT.directBuffer(1 + 4 + cmdNameBytes.length);
            buf.writeByte(CloudBoxCommandsList.CMD_SIGNAL_BYTE);
            buf.writeInt(cmdNameBytes.length);
            buf.writeBytes(cmdNameBytes);
            ctx.channel().writeAndFlush(buf);
        }
    }
}
