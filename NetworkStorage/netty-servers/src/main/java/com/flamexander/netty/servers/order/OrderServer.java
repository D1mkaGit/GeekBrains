package com.flamexander.netty.servers.order;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class OrderServer {
    public void run() throws Exception {
        class InboundTest extends ChannelInboundHandlerAdapter {
            private String name;
            private boolean answer;

            public InboundTest(String name, boolean answer) {
                this.name = name + "(In)";
                this.answer = answer;
            }

            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                System.out.println(this.name);
                if (answer) {
//                     ctx.channel().writeAndFlush("xxx");
                    ctx.writeAndFlush("xxx");
                }
                ctx.fireChannelRead(msg);
            }
        }

        class OutboundTest extends ChannelOutboundHandlerAdapter {
            private String name;

            public OutboundTest(String name) {
                this.name = name + "(Out)";
            }

            @Override
            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                System.out.println(this.name + ": " + msg);
                ctx.writeAndFlush(msg);
            }
        }

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(
                                    new InboundTest("1", false), new InboundTest("2", true), new OutboundTest("2"), new OutboundTest("1"), new InboundTest("3", true)
                            );
                        }
                    })
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture f = b.bind(8189).sync();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new OrderServer().run();
    }
}
