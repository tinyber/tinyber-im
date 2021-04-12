package com.tiny.chat.server.handler;

import java.util.Properties;

public class ServerHandlerConfiguration {

	private  String name ;

	private  String serverHandler ;

	public ServerHandlerConfiguration(){}

	public ServerHandlerConfiguration(String name, Properties prop){
		this.name = name;
		this.serverHandler = prop.getProperty(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getServerHandler() {
		return serverHandler;
	}

	public void setServerHandler(String serverHandler) {
		this.serverHandler = serverHandler;
	}


}
