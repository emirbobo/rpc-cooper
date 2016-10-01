package com.cooper.rpc;

import com.cooper.rpc.code.CooperClientDecoder;
import com.cooper.rpc.code.CooperClientEncoder;
import com.cooper.rpc.handler.CooperClientBizHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * Created by emirbobo on 2016/9/30.
 */
public class ClientChannelInit extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
//        pipeline.addLast(new LengthFieldBasedFrameDecoder(1024,0,4,0,4));
//        pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Unpooled.wrappedBuffer(Constants.instance.PackageDelimiter)));
//        pipeline.addLast(new StringDecoder());
//        pipeline.addLast(new StringEncoder());
        pipeline.addLast(new CooperClientDecoder());
        pipeline.addLast(new CooperClientEncoder());
        pipeline.addLast(new CooperClientBizHandler());
    }
}
