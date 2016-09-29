package com.fwtest.common;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2016/9/29.
 */
public class JsonMessageDecoder extends MessageToMessageDecoder<CharSequence>
{
	@Override
	protected void decode(ChannelHandlerContext ctx, CharSequence msg, List<Object> out) throws Exception {
		out.add(new JSONObject(String.valueOf(msg)));
	}
}
