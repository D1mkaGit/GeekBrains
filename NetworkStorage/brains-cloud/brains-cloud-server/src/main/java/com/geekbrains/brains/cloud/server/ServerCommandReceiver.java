package com.geekbrains.brains.cloud.server;


import com.geekbrains.brains.cloud.common.CloudBoxCommandsList;
import com.geekbrains.brains.cloud.common.CommandReceiver;
import com.geekbrains.brains.cloud.common.ProtoFileSender;
import io.netty.channel.ChannelHandlerContext;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.geekbrains.brains.cloud.common.CommonCommandSender.sendCommand;
import static com.geekbrains.brains.cloud.common.CommonCommandSender.sendFileListCmd;
import static com.geekbrains.brains.cloud.common.CommonFileRename.renameFileInLocation;
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
            sendFileListCmd(ctx, storageFolderName);
        }

        if (cmd.startsWith("/delete ")) {
            String fileToDeleteName = cmd.split("\\s")[1];
            Path fileWithPath = Paths.get(storageFolderName, fileToDeleteName);
            if (Files.exists(fileWithPath)) {
                System.out.println(fileToDeleteName + " пытается удалиться удален");
                if (Files.deleteIfExists(fileWithPath))
                    sendFileListCmd(ctx, storageFolderName);
            } else {
                System.out.println("Запрошенного файла (" + fileToDeleteName + ") на удаление не нашлось на сервере" +
                        "(" + storageFolderName + ")");
            }
        }

        if (cmd.startsWith("/rename ")) {
            String cmdToSplit = cmd.split("\\s")[1];
            String oldFileToRename = cmdToSplit.split("\\|")[0];
            String newFileToRename = cmdToSplit.split("\\|")[1];
            renameFileInLocation(storageFolderName, oldFileToRename, newFileToRename);
            sendFileListCmd(ctx, storageFolderName);
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
                sendCommand(ctx.channel(), "/authok " + userName, CloudBoxCommandsList.LOGIN_SIGNAL_BYTE);
                sendFileListCmd(ctx, storageFolderName);
                DbService.log(LOGIN, loginName);
            } else {
                System.out.println("Отправляем команду неудачного логина");
                sendCommand(ctx.channel(), "/authofail ", CloudBoxCommandsList.LOGIN_SIGNAL_BYTE);
                DbService.log(INCORRECT_LOGIN, loginName);
            }

        }
    }
}
