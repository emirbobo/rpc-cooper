package com.fwtest.client;

import com.fwtest.Constants;
import com.util.UtilConsole;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by Administrator on 2016/9/29.
 */
public class Client
{
	public static void main(String[] args) {


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
		Client client = new Client();
		client.run(host,port);
	}

	private void run(String host, int port) {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group)
					.channel(NioSocketChannel.class)
					.handler(new ClientInitializer());

			// Start the connection attempt.
			Channel ch = b.connect(host, port).sync().channel();

			ClientBizHandler.instance.run();
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
