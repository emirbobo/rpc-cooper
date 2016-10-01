package com.fwtest.server;

import cooper.rpc.Constants;
import cooper.rpc.UtilConsole;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;


/**
 * Created by Administrator on 2016/9/29.
 */
public class Server
{
	public Server() {
		super();
	}

	public static void main(String[] args) {

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
		Server server = new Server();
		server.run(host,port);
	}

	private void run(String host,int port) {
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
//					.handler(new LoggingHandler(LogLevel.INFO))
					.childHandler(new ServerInitializer());

			b.bind(host,port).sync().channel().closeFuture().sync();
		}
		catch (Exception e)
		{

		}
		finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
