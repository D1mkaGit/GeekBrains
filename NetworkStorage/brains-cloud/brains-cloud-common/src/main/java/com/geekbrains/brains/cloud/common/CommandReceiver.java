package com.geekbrains.brains.cloud.common;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;

import java.nio.charset.StandardCharsets;

public abstract class CommandReceiver {
    private State currentState = State.IDLE;
    private int commandTypeLength;
    private int receivedLength;
    private StringBuilder cmd;
    private ByteBuf buffer;

    public void startReceive() {
        currentState = State.COMMAND_LENGTH;
        cmd = new StringBuilder();
        buffer = ByteBufAllocator.DEFAULT.directBuffer(65536);
    }

    public void receive( ChannelHandlerContext ctx, ByteBuf buf, Runnable finishOperation ) throws Exception {
        if (currentState == State.COMMAND_LENGTH) {
            if (buf.readableBytes() >= 4) {
                System.out.println("STATE: Get command length");
                commandTypeLength = buf.readInt();
                if (commandTypeLength > buffer.capacity()) {
                    buffer.release();
                    buffer = ByteBufAllocator.DEFAULT.directBuffer(commandTypeLength);
                }
                currentState = State.COMMAND;
                receivedLength = 0;
                cmd.setLength(0);
            }
        }
        if (currentState == State.COMMAND) {
            while (buf.readableBytes() > 0) {
                buffer.writeByte(buf.readByte());
                receivedLength++;
                if (receivedLength == commandTypeLength) {
                    parseCommand(ctx, buffer.toString(StandardCharsets.UTF_8));
                    buffer.clear();
                    currentState = State.IDLE;
                    finishOperation.run();
                    return;
                }
            }
        }
    }

    public abstract void parseCommand( ChannelHandlerContext ctx, String cmd ) throws Exception;

    public enum State {
        IDLE, COMMAND_LENGTH, COMMAND
    }
}
