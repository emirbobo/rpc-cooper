package com.cooper.rpc.handler;

import com.cooper.rpc.body.RequestBody;
import com.cooper.rpc.body.ResponseBody;
import com.cooper.rpc.register.RPCRegisterHandler;
import com.esotericsoftware.minlog.Log;
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
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        super.channelWritabilityChanged(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RequestBody requestBody) throws Exception {
//        channelHandlerContext.channel().writeAndFlush("invoke --> "+requestBody.toString());
        System.out.println("invoke --> "+requestBody.toString());
        Channel channel = channelHandlerContext.channel();
        Object result = null;
        ResponseBody responseBody = new ResponseBody();
        try {
            result = RPCRegisterHandler.registor.findResult(requestBody);
            responseBody.setResultCode(0);
            responseBody.setInvokeResult(result);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        channel.writeAndFlush(responseBody);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {
        try {
            channelGroup.add(ctx.channel());
            this.channel = ctx.channel();
            System.out.println("connection success uid : "+uid.getAndIncrement());
//            channel.writeAndFlush(new ResponseBody(uid.getAndIncrement(), 0, "success").toString());
        }catch (Exception e){
            e.printStackTrace();
//            channel.writeAndFlush(new ResponseBody(uid.getAndIncrement(), 1, "failed").toString());
        }
//        channel.writeAndFlush(new RequestBody(uid.getAndIncrement()+"","RequestInterface","RequestMethod",null));
    }
}
