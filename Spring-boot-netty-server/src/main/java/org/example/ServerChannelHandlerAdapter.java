package org.example;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import javax.annotation.Resource;

/**
 * @ClassName ServerChannelHandlerAdapter
 * @Description ChannelHandlerAdapter
 * @Date 2020/7/7 17:32
 * @Author wangyong
 * @Version 1.0
 **/
@ChannelHandler.Sharable
public class ServerChannelHandlerAdapter extends ChannelHandlerAdapter {

    /**
     * 注入请求分排器
     */
    @Resource
    private RequestDispatcher dispatcher;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        dispatcher.dispatcher(ctx);
    }
}
