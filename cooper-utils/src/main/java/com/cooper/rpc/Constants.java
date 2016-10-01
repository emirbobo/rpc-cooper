package com.cooper.rpc;

/**
 * Created by Administrator on 2016/9/29.
 */
public enum Constants {
	instance;
	public String PackageDelimiterStr = "\0";
	public byte[] PackageDelimiter = PackageDelimiterStr.getBytes();
	public int DefaultPort = 9876;
	public String DefaultHost = "0.0.0.0";
	public String DefaultHostForClient = "127.0.0.1";


	public String Pack_CleintReceived_sender = "sender";
	public String Pack_CleintReceived_msg = "msg";

	public String Pack_CleintSend_msg = "msg";

	//"命令:" 执行命令
	public String Pack_FuncPrefix_Rename = "name:";

	public String Pack_Func_action = "action";
	public String Pack_Func_action_cmd_rename = "set-name";

	public String Pack_Func_action_data = "data";
}
