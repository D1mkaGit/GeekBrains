package com.flamexander.netty.servers.byte_sender;

import com.flamexander.netty.servers.decoding.DecodeServer;
import com.flamexander.netty.servers.decoding.DecodeServerHandler;
import com.flamexander.netty.servers.decoding.FourByteDecoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ByteSenderServer {
    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    System.out.println("Получили сообщение от клиента");
                                    byte[] arr = {65, 66, 67};

                                    ByteBufAllocator al = new PooledByteBufAllocator();
                                    // ByteBuf buf = al.buffer(arr.length);
                                    ByteBuf buf = Unpooled.copiedBuffer(arr);
                                    // buf.resetWriterIndex();
                                    // buf.writeBytes(arr);
                                    ctx.writeAndFlush(buf);
                                }
                            });
                        }
                    })
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            System.out.println("Сервер запущен");
            ChannelFuture f = b.bind(8189).sync();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            try {
                new ByteSenderServer().run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        Scanner sc = new Scanner(System.in);
        System.out.println("Нажмите кнопку");
        sc.nextLine();
        System.out.println("Нажали. Клиент начинает работу");
        sc.close();
        Socket socket = new Socket("localhost", 8189);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream());
        out.write((byte)65);
        out.flush();
        int x = 0;
        while ((x = in.read()) != -1) {
            System.out.println(x);
        }
        in.close();
        out.close();
        socket.close();
    }
}
