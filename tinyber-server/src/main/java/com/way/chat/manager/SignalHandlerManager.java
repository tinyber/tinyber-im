package com.tiny.chat.manager;

import com.tiny.common.packet.Signal;
import com.tiny.chat.proess.ProcessorIntf;
import com.tiny.chat.signal.AbstractSignalHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@SuppressWarnings("unchecked")
public class SignalHandlerManager {

	/**
	 * 通用cmd处理命令
	 */
	private static final Map<Signal, AbstractSignalHandler> handlerMap = new HashMap<>();

	private static final Logger logger = LoggerFactory.getLogger(SignalHandlerManager.class);

	private SignalHandlerManager(){};

	static{
		 try {
			List<SignalHandlerConfiguration> configurations = SignalHandlerConfigurationFactory.parseConfiguration();
			init(configurations);
		} catch (Exception e) {
			 logger.error(e.toString(),e);
		}
	}

	private static void init(List<SignalHandlerConfiguration> configurations) throws Exception{
		for(SignalHandlerConfiguration configuration : configurations){
			Class<AbstractSignalHandler> cmdHandlerClazz = (Class<AbstractSignalHandler>)Class.forName(configuration.getCmdHandler());
			AbstractSignalHandler signalHandler = cmdHandlerClazz.newInstance();
			List<String> proCmdHandlerList = configuration.getProCmdhandlers();
			if(!proCmdHandlerList.isEmpty()){
				for(String proCmdHandlerClass : proCmdHandlerList){
					Class<ProcessorIntf> proCmdHandlerClazz = (Class<ProcessorIntf>)Class.forName(proCmdHandlerClass);
					ProcessorIntf proCmdHandler = proCmdHandlerClazz.newInstance();
					//signalHandler.addProcessor(proCmdHandler);
				}
			}
			registerHandler(signalHandler);
		}
	}
	public static AbstractSignalHandler registerHandler(AbstractSignalHandler imServerHandler) throws Exception{
		if(imServerHandler == null || imServerHandler.signal() == null) {
			return null;
		}
		int signNum = imServerHandler.signal().getValue();
		if(Signal.forNumber(signNum) == null) {
			throw new Exception("注册cmd处理器失败,不合法的cmd命令码:"+signNum+",请在Command枚举类中添加!");
		}
		if(handlerMap.get(imServerHandler.signal()) == null)
		{
			return handlerMap.put(imServerHandler.signal(),imServerHandler);
		}
		return null;
	}

	public static AbstractSignalHandler removeCommand(Signal signal){
		if(signal == null) {
			return null;
		}
		if(handlerMap.get(signal) != null)
		{
			return handlerMap.remove(signal);
		}
		return null;
	}

	public static <T> T getSignal(Signal signal,Class<T> clazz){
		AbstractSignalHandler cmdHandler = getSignal(signal);
		if(cmdHandler != null){
			return (T)cmdHandler;
		}
		return null;
	}

	public static AbstractSignalHandler getSignal(Signal signal){
		if(signal == null) {
			return null;
		}
		return handlerMap.get(signal);
	}
}
