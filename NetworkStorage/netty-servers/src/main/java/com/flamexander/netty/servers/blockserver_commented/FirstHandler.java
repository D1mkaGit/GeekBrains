package com.flamexander.netty.servers.blockserver_commented;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Arrays;

public class FirstHandler extends ChannelInboundHandlerAdapter {
    // Что делать, когда к нам прилетело сообщение?
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // Поскольку этот хендлер стоит "первым от сети", то 100% получим ByteBuf
        ByteBuf buf = (ByteBuf)msg;
        // Ждем получения трех байт
        if (buf.readableBytes() < 3) {
            return;
        }
        // Как только получили три байта, готовим массив, чтобы их туда закинуть
        byte[] data = new byte[3];
        // Перекидываем байты из буфера в массив
        buf.readBytes(data);
        // Освобождаем буфер
        buf.release();
        // Распечатываем что за массив у нас получился
        System.out.println(Arrays.toString(data));
        // Прокидываем массив дальше по конвееру
        ctx.fireChannelRead(data);
    }

    // Стандартный обработчик исключений. Не забывайте его добавлять!!!
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
