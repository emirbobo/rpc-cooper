package com.cooper.rpc.code;

import cooper.rpc.ObjectSerialize;
import cooper.rpc.body.RequestBody;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by emirbobo on 2016/9/30.
 */
public class CooperClientEncoder extends MessageToByteEncoder<RequestBody> {
//    @Override
//    protected void encode(ChannelHandlerContext channelHandlerContext, RequestBody requestBody, List<Object> list) throws Exception {
//        list.add(ObjectSerialize.serialize(requestBody));
//    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, RequestBody requestBody, ByteBuf byteBuf) throws Exception {
        byte[] bs = ObjectSerialize.serialize(requestBody);
        System.out.println("total bytes ["+bs.length+"]");
        byteBuf.writeInt(bs.length);
        byteBuf.writeBytes(bs);
    }
}
