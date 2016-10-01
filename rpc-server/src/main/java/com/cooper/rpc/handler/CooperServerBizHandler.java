package com.cooper.rpc.handler;

import cooper.rpc.body.RequestBody;
import cooper.rpc.body.ResponseBody;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by emirbobo on 2016/9/30.
 */
public class CooperServerBizHandler extends SimpleChannelInboundHandler<RequestBody> {

    ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private Channel channel;

    AtomicInteger uid = new AtomicInteger(0);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RequestBody requestBody) throws Exception {
        channelHandlerContext.channel().writeAndFlush("invoke --> "+requestBody.toString());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {
        try {
            channelGroup.add(ctx.channel());
            this.channel = ctx.channel();
            channel.writeAndFlush(new ResponseBody(uid.getAndIncrement(), 0, "success").toString());
        }catch (Exception e){
            e.printStackTrace();
            channel.writeAndFlush(new ResponseBody(uid.getAndIncrement(), 1, "failed").toString());
        }
//        channel.writeAndFlush(new RequestBody(uid.getAndIncrement()+"","RequestInterface","RequestMethod",null));
    }
}
