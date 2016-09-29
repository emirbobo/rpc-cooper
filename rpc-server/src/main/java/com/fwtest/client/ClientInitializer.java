package com.fwtest.client;

import com.fwtest.Constants;
import com.fwtest.common.JsonMessageDecoder;
import com.fwtest.common.JsonMessageEncoder;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


/**
 * Created by Administrator on 2016/9/29.
 */
public class ClientInitializer extends ChannelInitializer<SocketChannel>
{
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Unpooled.wrappedBuffer(Constants.instance.PackageDelimiter)));
		pipeline.addLast(new StringDecoder());
		pipeline.addLast(new StringEncoder());
		pipeline.addLast(new JsonMessageDecoder());
		pipeline.addLast(new JsonMessageEncoder());
		pipeline.addLast(new ClientBizHandler());
	}
}
