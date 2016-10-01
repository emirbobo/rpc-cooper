package com.cooper.rpc.channel;

import com.cooper.rpc.coder.CooperServerDecoder;
import com.cooper.rpc.coder.CooperServerEncoder;
import com.cooper.rpc.handler.CooperServerBizHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * Created by emirbobo on 2016/9/30.
 */
public class ServerChannelInit extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
//        pipeline.addLast(new LengthFieldBasedFrameDecoder(1024,0,4,0,4));
//        pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Unpooled.wrappedBuffer(Constants.instance.PackageDelimiter)));
//        pipeline.addLast(new StringDecoder());
//        pipeline.addLast(new StringEncoder());
        pipeline.addLast(new CooperServerDecoder());
        pipeline.addLast(new CooperServerEncoder());
        pipeline.addLast(new CooperServerBizHandler());
    }
}
