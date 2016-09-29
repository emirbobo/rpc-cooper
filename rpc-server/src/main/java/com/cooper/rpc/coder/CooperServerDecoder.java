package com.cooper.rpc.coder;

import com.cooper.rpc.body.ResponseBody;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * Created by emirbobo on 2016/9/30.
 */
public class CooperServerDecoder extends MessageToMessageDecoder<ResponseBody> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ResponseBody responseBody, List<Object> list) throws Exception {
        list.add(responseBody);
    }
}
