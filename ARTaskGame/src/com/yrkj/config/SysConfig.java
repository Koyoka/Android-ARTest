package com.yrkj.config;

public class SysConfig {

	static Boolean IsDebug = true;
	public final static String SERVICE_HOST = IsDebug ? "192.168.1.104" : "121.199.17.68";
	public final static int SERVICE_HTTP_PORT = IsDebug ? 8888 : 80;
}
