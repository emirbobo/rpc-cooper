package com.fwtest.client;

import cooper.rpc.Constants;
import cooper.rpc.UtilConsole;
import cooper.rpc.UtilJson;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2016/9/29.
 */
public class ClientBizHandler extends SimpleChannelInboundHandler<JSONObject> {
	static ClientBizHandler instance;
	private Channel channel;

	protected ClientBizHandler() {
		instance = this;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		this.channel = ctx.channel();
		UtilConsole.log("connected");
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, JSONObject pack) throws Exception
	{
		String sender = UtilJson.get(pack, Constants.instance.Pack_CleintReceived_sender);
		String msg = UtilJson.get(pack, Constants.instance.Pack_CleintReceived_msg);
		UtilConsole.log(sender+" 说 : "+msg);
	}

	public void run() {
		// Read commands from the stdin.
		ChannelFuture lastWriteFuture = null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			UtilConsole.log("可以开始聊天了\n输入name:新名字 改名\n输入 bye 退出");
			while (true){
				String line = in.readLine();
				if (line == null) {
					break;
				}

				// Sends the received line to the server.
				lastWriteFuture = channel.writeAndFlush(createMessagePack(line));

				// If user typed the 'bye' command, wait until the server closes
				// the connection.
				if ("bye".equals(line.toLowerCase())) {
					channel.closeFuture().sync();
					break;
				}
			}
			// Wait until all messages are flushed before closing the channel.
			if (lastWriteFuture != null) {
				lastWriteFuture.sync();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JSONObject createMessagePack(String msg) {
		JSONObject data = new JSONObject();
		if(msg.startsWith(Constants.instance.Pack_FuncPrefix_Rename))
		{
			UtilJson.put(data,Constants.instance.Pack_Func_action,Constants.instance.Pack_Func_action_cmd_rename);
			String [] segs = msg.split(":");
			UtilJson.put(data,Constants.instance.Pack_Func_action_data,segs[1].trim());
		}
		else
			UtilJson.put(data,Constants.instance.Pack_CleintSend_msg,msg);
		return data;
	}
}
