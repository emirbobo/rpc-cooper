package com.cooper.rpc.coder;

import com.cooper.rpc.kryo.KryoCodecUtil;
import com.cooper.rpc.kryo.KryoPoolFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by emirbobo on 2016/9/30.
 */
public class CooperServerDecoder extends ByteToMessageDecoder {
//    @Override
//    protected void decode(ChannelHandlerContext channelHandlerContext, byte[] responseBody, List<Object> list) throws Exception {
//        list.add(ObjectSerialize.deSerialize(responseBody));
//    }

    KryoCodecUtil kryoCodecUtil = new KryoCodecUtil(KryoPoolFactory.getKryoPoolInstance());
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> objects) throws Exception {
        System.out.println(byteBuf.readableBytes());
        int totalbytes = byteBuf.readableBytes();
        if(totalbytes < 4){
            return;
        }
        int len = byteBuf.readInt();
        if(len <= byteBuf.readableBytes()){

            byte[] bs = new byte[len];
            byteBuf.readBytes(bs);
//            objects.add(ObjectSerialize.deSerialize(bs));
            objects.add(kryoCodecUtil.decode(bs));
        }else{
            byteBuf.resetReaderIndex();
        }
    }
}
