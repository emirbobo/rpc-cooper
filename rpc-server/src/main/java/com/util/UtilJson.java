package com.util;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/9/24.
 */
public class UtilJson {
	public static void put(JSONObject log, String name, Object value) {
		try {
			log.put(name,value);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public static String get(JSONObject msg, String sender) {
		try {
			return msg.getString(sender);
		} catch (JSONException e) {
//			e.printStackTrace();
		}
		return null;
	}
}
