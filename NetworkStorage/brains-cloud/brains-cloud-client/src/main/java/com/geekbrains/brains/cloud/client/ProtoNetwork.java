package com.geekbrains.brains.cloud.client;

import com.geekbrains.brains.cloud.common.Callback;
import com.geekbrains.brains.cloud.common.ProtoHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.concurrent.CountDownLatch;

public class ProtoNetwork {
    private static final ProtoNetwork ourInstance = new ProtoNetwork();
    private Channel currentChannel;

    private ProtoNetwork() {
    }

    public static ProtoNetwork getInstance() {
        return ourInstance;
    }

    public Channel getCurrentChannel() {
        return currentChannel;
    }

    public void setOnReceivedCallback( Callback onReceivedCallback ) {
        currentChannel.pipeline().get(ProtoHandler.class).setOnReceivedCallback(onReceivedCallback);
    }

    public void setOnReceivedFLCallback( Callback onReceivedFLCallback ) {
        currentChannel.pipeline().get(ProtoHandler.class).setOnReceivedFLCallback(onReceivedFLCallback);
    }

    public void setOnReceivedLoginCallback( Callback onReceivedLoginCallback ) {
        currentChannel.pipeline().get(ProtoHandler.class).setOnReceivedLoginCallback(onReceivedLoginCallback);
    }

    public void start( CountDownLatch countDownLatch ) {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap clientBootstrap = new Bootstrap();
            clientBootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress("localhost", 8189))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel( SocketChannel socketChannel ) {
                            socketChannel.pipeline().addLast(new ProtoHandler("client_storage", new ClientCommandReceiver()));
                            currentChannel = socketChannel;
                        }
                    });
            ChannelFuture channelFuture = clientBootstrap.connect().sync();
            countDownLatch.countDown();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                group.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
