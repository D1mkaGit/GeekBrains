package com.flamexander.netty.servers.one_byte_buf_send;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class OneByteBufSendApp {
    static Channel channel;

    public static void main(String[] args) {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(8189)) {
                Socket socket = serverSocket.accept();
                DataInputStream in = new DataInputStream(socket.getInputStream());
                System.out.println("Connected...");
                int bytesReceived = 0;
                while (true) {
                    int x;
                    x = in.read();
                    if (x == -1) {
                        break;
                    }
                    bytesReceived++;
                    if (bytesReceived % 1024 == 0) {
                        System.out.println(bytesReceived);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap clientBootstrap = new Bootstrap();
            clientBootstrap.group(group);
            clientBootstrap.channel(NioSocketChannel.class);
            clientBootstrap.remoteAddress(new InetSocketAddress("localhost", 8189));
            clientBootstrap.handler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast();
                    channel = socketChannel;
                }
            });
            ChannelFuture channelFuture = clientBootstrap.connect().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ByteBufAllocator alloc = new UnpooledByteBufAllocator(true);
        byte[] pack = new byte[1024];
        Arrays.fill(pack, (byte) 1);
        ByteBuf buf = alloc.directBuffer(1024);
        buf.writeBytes(pack);
        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            channel.writeAndFlush(buf);
            buf.retain();
        }
    }
}
