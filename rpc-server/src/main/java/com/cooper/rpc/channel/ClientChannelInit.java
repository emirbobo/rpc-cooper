package com.cooper.rpc.channel;

import com.cooper.rpc.coder.CooperServerDecoder;
import com.cooper.rpc.coder.CooperServerEncoder;
import com.cooper.rpc.handler.CooperClientBizHandler;
import com.fwtest.Constants;
import com.fwtest.client.ClientBizHandler;
import com.fwtest.common.JsonMessageDecoder;
import com.fwtest.common.JsonMessageEncoder;
import com.fwtest.server.ServerBizHandler;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Created by emirbobo on 2016/9/30.
 */
public class ClientChannelInit extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
//        pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Unpooled.wrappedBuffer(Constants.instance.PackageDelimiter)));
//        pipeline.addLast(new StringDecoder());
//        pipeline.addLast(new StringEncoder());
        pipeline.addLast(new CooperServerDecoder());
        pipeline.addLast(new CooperServerEncoder());
        pipeline.addLast(new CooperClientBizHandler());
    }
}
