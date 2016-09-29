package com.fwtest.common;

import com.fwtest.Constants;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2016/9/29.
 */
public class JsonMessageEncoder extends MessageToMessageEncoder<JSONObject>
{
	@Override
	protected void encode(ChannelHandlerContext ctx, JSONObject msg, List<Object> out) throws Exception {
		out.add(msg.toString() );
		out.add( Constants.instance.PackageDelimiterStr);
	}
}
