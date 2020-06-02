package com.geekbrains.brains.cloud.client;

import com.geekbrains.brains.cloud.common.CommandReceiver;
import io.netty.channel.ChannelHandlerContext;

public class ClientCommandReceiver extends CommandReceiver {
    @Override
    public void parseCommand( ChannelHandlerContext ctx, String cmd ) {
        if (cmd.startsWith("/filesList ")) {
            if (cmd.equals("/filesList ")) MainController.serverFilesListString = "";
            else MainController.serverFilesListString = cmd.split("\\s")[1];
            // не придумал ничего лучше, чем сделать это через статическую переменную
        }

        if (cmd.startsWith("/authok ")) {
            MainController.isAuthorized = true;
            MainController.loggedInUserName = cmd.split("\\s")[1];
            System.out.println("logged in ok");
        }
        if (cmd.startsWith("/authofail ")) {
            //
            MainController.isAuthorized = false;
            MainController.loggedInUserName = null;
            System.out.println("not logged in");
        }
    }
}
