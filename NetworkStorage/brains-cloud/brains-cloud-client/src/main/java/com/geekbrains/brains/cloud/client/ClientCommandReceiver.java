package com.geekbrains.brains.cloud.client;

import com.geekbrains.brains.cloud.common.CommandReceiver;
import io.netty.channel.ChannelHandlerContext;

public class ClientCommandReceiver extends CommandReceiver {
    @Override
    public void parseCommand( ChannelHandlerContext ctx, String cmd ) {
        if (cmd.startsWith("/filesList ")) {
            MainController.serverFilesListString = cmd.split("\\s")[1];
            // не придумал ничего лучше, чем сделать это через статическую переменную
        }
    }
}
