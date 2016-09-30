package com.cooper.rpc.coder;

import com.cooper.rpc.body.RequestBody;
import com.cooper.rpc.util.ObjectSerialize;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * Created by emirbobo on 2016/9/30.
 */
public class CooperServerEncoder extends MessageToByteEncoder<RequestBody> {
//    @Override
//    protected void encode(ChannelHandlerContext channelHandlerContext, RequestBody requestBody, List<Object> list) throws Exception {
//        list.add(ObjectSerialize.serialize(requestBody));
//    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, RequestBody requestBody, ByteBuf byteBuf) throws Exception {
        byte[] bs = ObjectSerialize.serialize(requestBody);
        System.out.println("total bytes ["+bs.length+"]");
        byteBuf.writeBytes(bs);
    }
}
