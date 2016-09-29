package com.cooper.rpc.coder;

import com.cooper.rpc.body.RequestBody;
import com.cooper.rpc.body.ResponseBody;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * Created by emirbobo on 2016/9/30.
 */
public class CooperServerEncoder extends MessageToMessageEncoder<RequestBody> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, RequestBody requestBody, List<Object> list) throws Exception {
        list.add(requestBody);
    }
}
