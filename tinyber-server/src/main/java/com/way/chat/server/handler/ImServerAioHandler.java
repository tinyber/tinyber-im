package com.tiny.chat.server.handler;

import com.tiny.common.config.ImConfig;
import com.tiny.common.packet.ImSessionContext;
import org.tio.core.ChannelContext;
import org.tio.core.TioConfig;
import org.tio.core.exception.TioDecodeException;
import org.tio.core.intf.Packet;
import org.tio.server.intf.ServerAioHandler;

import java.nio.ByteBuffer;

public class ImServerAioHandler implements ServerAioHandler {

	/**
	 *
	 * @param packet
	 * @return
	 * @throws Exception
	 * @author: Wchao
	 * 2016年11月18日 上午9:37:44
	 *
	 */
	@Override
	public void handler(Packet packet, ChannelContext channelContext) throws Exception {
		ImSessionContext imSessionContext = (ImSessionContext)channelContext.get();
		AbstractServerHandler handler = (AbstractServerHandler)imSessionContext.getServerHandler();
		if(handler != null){
			handler.handler(packet, channelContext);
		}
	}

	/**
	 *
	 * @param packet
	 * @return
	 * @author: Wchao
	 * 2016年11月18日 上午9:37:44
	 *
	 */
	@Override
	public ByteBuffer encode(Packet packet, TioConfig tioConfig, ChannelContext channelContext) {
		ImSessionContext imSessionContext = (ImSessionContext)channelContext.get();
		AbstractServerHandler handler = (AbstractServerHandler)imSessionContext.getServerHandler();
		if(handler != null){
			return handler.encode(packet, tioConfig, channelContext);
		}
		return null;
	}
	/**
	 *
	 * @param buffer
	 * @return
	 * @throws TioDecodeException
	 * @author: Wchao
	 * 2016年11月18日 上午9:37:44
	 *
	 */
	@Override
	public Packet decode(ByteBuffer buffer, int limit, int position, int readableLength, ChannelContext channelContext) throws TioDecodeException {
		ImSessionContext imSessionContext = (ImSessionContext)channelContext.get();
		AbstractServerHandler handler = null;
		if(imSessionContext == null){
			handler = ServerHandlerManager.initServerHandlerToChannelContext(buffer, channelContext);
			TioConfig tioConfig = ImConfig.tioConfig;
			//channelContext.setAttribute(Const.CHAT_QUEUE,new MsgQueueRunnable(channelContext,tioConfig.getTimExecutor()));
		}else{
			handler = (AbstractServerHandler)imSessionContext.getServerHandler();
		}
		if(handler != null){
			return handler.decode(buffer,limit,position,readableLength, channelContext);
		}else{
			throw new TioDecodeException("不支持的协议类型,无法找到对应的协议解码器!");
		}
	}
}
