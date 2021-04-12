package com.tiny.chat.constant;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 *
 */
public interface ImConstant {
	/**
	 * 服务器地址
	 */
	public static final String SERVER = "127.0.0.1";

	/**
	 * 监听端口
	 */
	public static final int PORT = 6789;

	public  static  final Charset CHAR_SET= StandardCharsets.UTF_8;

	/**
	 * 心跳超时时间
	 */
	public static final int TIMEOUT = 50000;
}
