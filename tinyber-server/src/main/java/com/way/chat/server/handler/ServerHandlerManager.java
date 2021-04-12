package com.tiny.chat.server.handler;

import com.tiny.chat.config.ImConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerHandlerManager{

	private static final Logger logger = LoggerFactory.getLogger(ServerHandlerManager.class);

	private static final Map<String,AbstractServerHandler> serverHandlers = new HashMap<String,AbstractServerHandler>();

	static{
		 try {
			List<ServerHandlerConfiguration> configurations = ServerHandlerConfigurationFactory.parseConfiguration();
			init(configurations);
		} catch (Exception e) {
			logger.error(e.toString(),e);
		}
	}

	private static void init(List<ServerHandlerConfiguration> configurations) throws Exception{
		for(ServerHandlerConfiguration configuration : configurations){
			Class<AbstractServerHandler> serverHandlerClazz = (Class<AbstractServerHandler>)Class.forName(configuration.getServerHandler());
			AbstractServerHandler serverdHandler = serverHandlerClazz.newInstance();
			addServerHandler(serverdHandler);
		}
	}

	public static AbstractServerHandler addServerHandler(AbstractServerHandler serverHandler){
		if(serverHandler == null) {
			return null;
		}
		return serverHandlers.put(serverHandler.name(),serverHandler);
	}

	public static AbstractServerHandler removeServerHandler(String name){
		if(name == null || "".equals(name)) {
			return null;
		}
		return serverHandlers.remove(name);
	}

	public static AbstractServerHandler initServerHandlerToChannelContext(ByteBuffer buffer, ChannelContext channelContext){
		for(Map.Entry<String,AbstractServerHandler> entry : serverHandlers.entrySet()){
			ByteBuffer copyByteBuffer = null;
			if(buffer != null && channelContext.get() == null){
				copyByteBuffer = ByteBuffer.wrap(buffer.array());
			}
			AbstractServerHandler serverHandler = entry.getValue();
			try {
				if(serverHandler.isProtocol(copyByteBuffer,channelContext)){
					return serverHandler;
				}
			} catch (Throwable e) {
				logger.error(e.getMessage());
			}
		}
		return null;
	}

	public static <T> T getServerHandler(String name,Class<T> clazz){
		AbstractServerHandler  serverHandler = serverHandlers.get(name);
		if(serverHandler == null) {
			return null;
		}
		return (T)serverHandler;
	}

	public static <T> T getServerHandler(String name){
		AbstractServerHandler  serverHandler = serverHandlers.get(name);
		if(serverHandler == null) {
			return null;
		}
		return (T)serverHandler;
	}

	public static void init(ImConfig imConfig){
		for(Map.Entry<String,AbstractServerHandler> entry : serverHandlers.entrySet()){
			try {
				entry.getValue().init(imConfig);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
	}
}
