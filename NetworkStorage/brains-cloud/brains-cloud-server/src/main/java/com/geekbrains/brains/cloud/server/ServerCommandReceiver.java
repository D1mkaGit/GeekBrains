package com.geekbrains.brains.cloud.server;


import com.geekbrains.brains.cloud.common.CloudBoxCommandsList;
import com.geekbrains.brains.cloud.common.CommandReceiver;
import com.geekbrains.brains.cloud.common.ProtoFileSender;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static com.geekbrains.brains.cloud.server.DbService.LogEventType.INCORRECT_LOGIN;
import static com.geekbrains.brains.cloud.server.DbService.LogEventType.LOGIN;

public class ServerCommandReceiver extends CommandReceiver {
    @Override
    public void parseCommand( ChannelHandlerContext ctx, String cmd ) throws Exception {
        String storageFolderName = "server_storage";
        if (cmd.startsWith("/request ")) {
            String fileToClientName = cmd.split("\\s")[1];
            ProtoFileSender.sendFile(Paths.get(storageFolderName, fileToClientName), ctx.channel(), null);
        }

        if (cmd.startsWith("/list ")) {
            String filesList = Files.list(Paths.get(storageFolderName))
                    .filter(p -> !Files.isDirectory(p))
                    .map(p -> p.getFileName().toString())
                    .collect(Collectors.joining("|"));

            sendCommand(ctx, "/filesList " + filesList, CloudBoxCommandsList.CMD_SIGNAL_BYTE);
        }

        if (cmd.startsWith("/delete ")) {
            String fileToDeleteName = cmd.split("\\s")[1];
            Path fileWithPath = Paths.get(storageFolderName, fileToDeleteName);
            if (Files.exists(fileWithPath)) {
                System.out.println(fileToDeleteName + " удален");
                System.out.println(Files.deleteIfExists(fileWithPath));
            } else {
                System.out.println("Запрошенного файла (" + fileToDeleteName + ") на удаление не нашлось на сервере" +
                        "(" + storageFolderName + ")");
            }
        }

        if (cmd.startsWith("/auth ")) {
            String loginAndPass = cmd.split("\\s")[1];
            String loginName = null;
            String passHash = null;
            if (loginAndPass.contains("|")) {
                loginName = loginAndPass.split("\\|")[0];
                passHash = loginAndPass.split("\\|")[1];
            }
            String userName = DbService.getNickByLoginAndPass(loginName, passHash);
            System.out.println(userName);
            if (userName != null) {
                System.out.println("Отправляем команду успешного логина");
                sendCommand(ctx, "/authok " + userName, CloudBoxCommandsList.LOGIN_SIGNAL_BYTE);
                DbService.log(LOGIN, loginName);
            } else {
                System.out.println("Отправляем команду неудачного логина");
                sendCommand(ctx, "/authofail ", CloudBoxCommandsList.LOGIN_SIGNAL_BYTE);
                DbService.log(INCORRECT_LOGIN, loginName);
            }

        }
    }

    private void sendCommand( ChannelHandlerContext ctx, String s, byte cmdSignalByte ) {
        byte[] cmdNameBytes = (s).getBytes(StandardCharsets.UTF_8);
        ByteBuf buf = ByteBufAllocator.DEFAULT.directBuffer(1 + 4 + cmdNameBytes.length);
        buf.writeByte(cmdSignalByte);
        buf.writeInt(cmdNameBytes.length);
        buf.writeBytes(cmdNameBytes);
        ctx.channel().writeAndFlush(buf);
    }
}
