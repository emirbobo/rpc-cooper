package com.cooper.rpc;

import com.cooper.rpc.channel.ClientChannelInit;
import com.cooper.rpc.handler.CooperClientBizHandler;
import com.fwtest.Constants;
import com.fwtest.client.ClientInitializer;
import com.util.UtilConsole;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by xijingbo on 2016-09-29.
 */
public class NettyRPCClient {

    public static void main(String args[]) {


        String host = Constants.instance.DefaultHostForClient;
        int port = Constants.instance.DefaultPort;

        if(args.length < 2)
        {
            UtilConsole.error("启动需要两个参数,服务器ip和端口，默认链接 " + Constants.instance.DefaultHostForClient + ":" + Constants.instance.DefaultPort);
        }
        else
        {
            host = args[0];
            port = Integer.parseInt(args[1]);
        }
        UtilConsole.log("启动 " + host + ":" + port);
        NettyRPCClient client = new NettyRPCClient();
        client.run(host,port);
    }

    public void run(String host,int port){
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientChannelInit());

            // Start the connection attempt.
//            io.netty.channel.Channel ch = b.connect(host, port).sync().channel();
//
//            CooperClientBizHandler.instance.run();

            ChannelFuture f = b.connect(host, port).sync(); // (5)

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();


        }
        catch (Exception e)
        {
            e.printStackTrace();

        }finally {
            // The connection is closed automatically on shutdown.
            group.shutdownGracefully();
        }
    }
}
