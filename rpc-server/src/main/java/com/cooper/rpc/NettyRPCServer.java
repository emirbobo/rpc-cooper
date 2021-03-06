package com.cooper.rpc;


import com.cooper.rpc.body.TestInterface;
import com.cooper.rpc.channel.ServerChannelInit;
import com.cooper.rpc.curator.CuratorClient;
import com.cooper.rpc.register.RPCRegisterHandler;
import com.testcode.PrintTimeImpl;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;


/**
 * Created by xijingbo on 2016-09-29.
 */
public class NettyRPCServer {


    /**
     * 注册接口调用
     * @param host
     * @param port
     * @param o
     */
    public static void register(String host,int port,Class o) throws Exception {
        RPCRegisterHandler.registor.register(o);
        CuratorClient.createNode(host,port,o);
        new NettyRPCServer(host,8008).start();
    }

    public static void main1(String [] args) throws Exception {
        register("localhost",2181,PrintTimeImpl.class);
    }
    public static void main(String [] args){
        RPCRegisterHandler.registor.register(PrintTimeImpl.class);
        System.out.println(NettyRPCServer.class.getCanonicalName());
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

    public NettyRPCServer(){

    }

    private int port;
    private String host;
    private NettyRPCServer(String host,int port) {
        this.host = host;
        this.port = port;
    }

    private void start(){
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

}
