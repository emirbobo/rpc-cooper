package com.cooper.rpc.handler;

import com.cooper.rpc.body.RequestBody;
import com.cooper.rpc.body.ResponseBody;
import com.fwtest.Constants;
import com.util.UtilConsole;
import com.util.UtilJson;
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
public class CooperClientBizHandler extends SimpleChannelInboundHandler<RequestBody> {
	public static CooperClientBizHandler instance;
	private Channel channel;

	public CooperClientBizHandler() {
		instance = this;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		this.channel = ctx.channel();
		UtilConsole.log("connected");
        UtilConsole.log("可以开始调用远程方法了\n输入类名方法名");

        run();
	}

    ChannelFuture lastWriteFuture = null;

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, RequestBody pack) throws Exception
	{

	}

	public void run() {
		// Read commands from the stdin.
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

			while (true){
				String line = in.readLine();
				if (line == null) {
					break;
				}

				// Sends the received line to the server.
				lastWriteFuture = channel.writeAndFlush(new ResponseBody(0,line+"调用成功"));

				// If user typed the 'bye' command, wait until the server closes
				// the connection.
				if ("bye".equals(line.toLowerCase())) {
					channel.closeFuture().sync();
					break;
				}// Wait until all messages are flushed before closing the channel.
			}
            if (lastWriteFuture != null) {
                lastWriteFuture.sync();
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	private JSONObject createMessagePack(String msg) {
//		JSONObject data = new JSONObject();
//		if(msg.startsWith(Constants.instance.Pack_FuncPrefix_Rename))
//		{
//			UtilJson.put(data,Constants.instance.Pack_Func_action,Constants.instance.Pack_Func_action_cmd_rename);
//			String [] segs = msg.split(":");
//			UtilJson.put(data,Constants.instance.Pack_Func_action_data,segs[1].trim());
//		}
//		else
//			UtilJson.put(data,Constants.instance.Pack_CleintSend_msg,msg);
//		return data;
//	}
}
