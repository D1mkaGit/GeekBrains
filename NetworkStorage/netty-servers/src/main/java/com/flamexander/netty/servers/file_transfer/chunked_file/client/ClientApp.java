package com.flamexander.netty.servers.file_transfer.chunked_file.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.FileRegion;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.CountDownLatch;

public class ClientApp {
    public static void main(String[] args) throws Exception {
        String filename = "demo.txt";

        CountDownLatch networkStarter = new CountDownLatch(1);
        new Thread(() -> Network.getInstance().start(networkStarter)).start();
        networkStarter.await();
        System.out.println("Сетевое подключение открыто");
        File file = new File(filename);
        FileInputStream in = new FileInputStream(file);
        FileRegion region = new DefaultFileRegion(in.getChannel(), 0, file.length());

        ChannelFuture transferOperationFuture = Network.getInstance().getCurrentChannel().writeAndFlush(region);
        transferOperationFuture.addListener(
                (ChannelFutureListener) future -> {
                    if (!future.isSuccess()) {
                        future.cause().printStackTrace();
                    }
                    if (future.isSuccess()) {
                        System.out.println("Файл успешно передан");
                        in.close();
                        Network.getInstance().stop();
                    }
                });
    }
}
