package com.fwtest.server;

import com.fwtest.Constants;
import com.util.UtilConsole;
import com.util.UtilJson;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Created by Administrator on 2016/9/29.
 */
public class ServerBizHandler extends SimpleChannelInboundHandler<JSONObject>
{
	static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	static final HashMap<Integer,ServerBizHandler> bizHandlers = new HashMap<>();
	static AtomicInteger _id = new AtomicInteger();

	int id = _id.incrementAndGet();
	String name = null;
	Object handlerLock = new Object();

	JSONObject toOthers;
	JSONObject toSelf;
	private Channel channel;

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception
	{
		channels.add(ctx.channel());
		this.channel = ctx.channel();
		ctx.channel().writeAndFlush(createReceivedInfoData(null, "Server", "你的编号是：" + id));
	}
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, JSONObject msg) throws Exception {
		UtilConsole.log("Received #" + id + " : " + msg);
		Channel channelCur = ctx.channel();

		handleMsg(msg);

		if(toOthers != null)
		{
			for(Channel channel : channels)
			{
				if(channel != channelCur)
				{
					channel.writeAndFlush(toOthers);
				}
				else
					channel.writeAndFlush(toSelf);
			}
		}
		else
			if(toSelf != null)
				channelCur.writeAndFlush(toSelf);
	}

	private void handleMsg(JSONObject msg) {
		toOthers = null;
		toSelf = null;
		String action = UtilJson.get(msg, Constants.instance.Pack_Func_action);
		if(action == null)
		{
			String msgContent = UtilJson.get(msg,Constants.instance.Pack_CleintReceived_msg);
			createReceivedInfoData(toOthers = new JSONObject(),name != null ? name : "#"+id,msgContent);
			createReceivedInfoData(toSelf = new JSONObject(), "我",msgContent);
			return;
		}
		if(Constants.instance.Pack_Func_action_cmd_rename.equals(action))
		{
			String name = UtilJson.get(msg,Constants.instance.Pack_Func_action_data);
			if(name != null)
			{
				name = name.trim();
				if(name.length() > 0)
				{
					this.name = name;
					createReceivedInfoData(toOthers = new JSONObject() ,"服务器","#"+id+" 改名为 "+name);
					createReceivedInfoData(toSelf = new JSONObject(), "服务器","我改名为 "+name);
					return;
				}
			}
			createReceivedInfoData(toSelf = new JSONObject(), "服务器","改名失败");
		}
	}

	private JSONObject createReceivedInfoData(JSONObject data,String sender, String msg)
	{
		if(data == null)
			data = new JSONObject();
		UtilJson.put(data,Constants.instance.Pack_CleintReceived_sender,sender);
		UtilJson.put(data,Constants.instance.Pack_CleintReceived_msg,msg);
		return data;
	}
}
