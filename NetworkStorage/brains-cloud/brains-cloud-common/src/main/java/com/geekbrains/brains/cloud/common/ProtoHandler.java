package com.geekbrains.brains.cloud.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ProtoHandler extends ChannelInboundHandlerAdapter {
    private final FileReceiver fileReceiver;
    private final CommandReceiver commandReceiver;
    private Status currentStatus;
    private final Runnable finishOperation = () -> {
        System.out.println("Операция завершена");
        if (onReceivedCallback != null) onReceivedCallback.callback();
        currentStatus = Status.IDLE;
    };
    private Callback onReceivedCallback;
    private Callback onReceivedFLCallback;

    public void setOnReceivedCallback( Callback onReceivedCallback ) {
        this.onReceivedCallback = onReceivedCallback;
    }

    public void setOnReceivedFLCallback( Callback onReceivedFLCallback ) {
        this.onReceivedFLCallback = onReceivedFLCallback;
    }

    private final Runnable finishCommand = () -> {
        System.out.println("Команда выполнена");
        if (onReceivedFLCallback != null) onReceivedFLCallback.callback();
        currentStatus = Status.IDLE;
    };

    public ProtoHandler( String rootDir, CommandReceiver commandReceiver ) {
        this.currentStatus = Status.IDLE;
        this.fileReceiver = new FileReceiver(rootDir);
        this.commandReceiver = commandReceiver;
    }

    @Override
    public void channelRead( ChannelHandlerContext ctx, Object msg ) throws Exception {
        ByteBuf buf = ((ByteBuf) msg);
        while (buf.readableBytes() > 0) {
            if (currentStatus == Status.IDLE) {
                byte controlByte = buf.readByte();
                if (controlByte == CloudBoxCommandsList.FILE_SIGNAL_BYTE) {
                    currentStatus = Status.FILE;
                    fileReceiver.startReceive();
                } else if (controlByte == CloudBoxCommandsList.CMD_SIGNAL_BYTE) {
                    currentStatus = Status.COMMAND;
                    commandReceiver.startReceive();
                }
            }
            if (currentStatus == Status.FILE) {
                fileReceiver.receive(ctx, buf, finishOperation);
            }
            if (currentStatus == Status.COMMAND) {
                commandReceiver.receive(ctx, buf, finishCommand);
            }
        }
        if (buf.readableBytes() == 0) {
            buf.release();
        }
    }

    private enum Status {
        IDLE, FILE, COMMAND
    }

    @Override
    public void exceptionCaught( ChannelHandlerContext ctx, Throwable cause ) {
        cause.printStackTrace();
        ctx.close();
    }
}
