package com.yrkj.config;

public class SysConfig {

	static Boolean IsDebug = false;
	public final static String SERVICE_HOST = IsDebug ? "192.168.1.104" : "121.199.17.68";
	public final static int SERVICE_HTTP_PORT = IsDebug ? 8888 : 80;
	public final static String DEFAULT_INTERFACE_NAME = "tsxz/interfaceYDTest_getClientMessage.do";
}
