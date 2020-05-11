package com.flamexander.netty.servers.safe_close;

import com.flamexander.netty.servers.serialization.CloudServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.*;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

public class MainApp {
    private CountDownLatch serverStarted;
    private CountDownLatch clientStarted;

    private class SafeHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            String inStr = (String) msg;
            if (inStr.equals("/exit")) {
                ctx.writeAndFlush("/close_connection");
                ctx.close();
            }
            System.out.println(inStr);
            ctx.writeAndFlush("Msg received");
        }
    }

    public void runServer() {
        EventLoopGroup mainGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(mainGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(
                                    new ObjectDecoder(1024 * 1024 * 1, ClassResolvers.cacheDisabled(null)),
                                    new ObjectEncoder(),
                                    new SafeHandler()
                            );
                        }
                    })
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            serverStarted.countDown();
            ChannelFuture future = b.bind(8189).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mainGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        MainApp app = new MainApp();
        app.serverStarted = new CountDownLatch(1);
        app.clientStarted = new CountDownLatch(1);
        new Thread(() -> app.runServer()).start();
        app.serverStarted.await();

        Socket socket = new Socket("localhost", 8189);
        ObjectEncoderOutputStream out = new ObjectEncoderOutputStream(socket.getOutputStream());
        ObjectDecoderInputStream in = new ObjectDecoderInputStream(socket.getInputStream());

        new Thread(() -> {
            try {
                app.clientStarted.countDown();
                while (true) {
                    String str = (String) in.readObject();
                    if(str.equals("/close_connection")) {
                        break;
                    }
                    System.out.println(str);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        app.clientStarted.await();

        out.writeObject("Java");
        out.writeObject("/exit");
    }
}
