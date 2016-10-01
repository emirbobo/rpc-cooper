package com.cooper.rpc;


import com.cooper.rpc.channel.ServerChannelInit;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by xijingbo on 2016-09-29.
 */
public class NettyRPCServer {

    public static void main(String [] args){
        String host = Constants.instance.DefaultHost;
        int port = Constants.instance.DefaultPort;

        if(args.length < 2)
        {
            UtilConsole.error("启动需要两个参数,绑定ip和端口，默认使用 " + Constants.instance.DefaultHost + ":" + Constants.instance.DefaultPort);
        }
        else
        {
            host = args[0];
            port = Integer.parseInt(args[1]);
        }
        UtilConsole.log("启动 " + host + ":" + port);
        NettyRPCServer server = new NettyRPCServer(host,port);
        server.start();
    }

    private int port;
    private String host;
    public NettyRPCServer(String host,int port) {
        this.host = host;
        this.port = port;
    }

    public void start(){
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            io.netty.bootstrap.ServerBootstrap b = new io.netty.bootstrap.ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
//					.handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ServerChannelInit());

            b.bind(host,port).sync().channel().closeFuture().sync();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public void destory(){

    }
}
