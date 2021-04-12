package com.tiny.chat.proess.in;

import com.tiny.common.Protocol;
import com.tiny.common.packet.ImPacket;
import com.tiny.common.packet.Signal;
import com.tiny.common.packet.ws.WsRequestPacket;
import com.tiny.common.packet.ws.WsResponsePacket;
import com.tiny.common.packet.ws.WsSessionContext;
import org.tio.core.ChannelContext;

public class WsHandshakeProcessor implements HandshakeProcessorIntf {

	/**
	 * 对httpResponsePacket参数进行补充并返回，如果返回null表示不想和对方建立连接，框架会断开连接，如果返回非null，框架会把这个对象发送给对方
	 * @param httpRequestPacket
	 * @param httpResponsePacket
	 * @param channelContext
	 * @return
	 * @throws Exception
	 * @author: Wchao
	 */
	@Override
	public ImPacket handshake(ImPacket packet, ChannelContext channelContext) throws Exception {
		WsRequestPacket wsRequestPacket = (WsRequestPacket) packet;
		WsSessionContext wsSessionContext = (WsSessionContext) channelContext.get();
		if (wsRequestPacket.isHandShake()) {
			WsResponsePacket wsResponsePacket = new WsResponsePacket();
			wsResponsePacket.setHandShake(true);
			wsResponsePacket.setSignal(Signal.PING);
			wsSessionContext.setHandshaked(true);
			return wsResponsePacket;
		}
		return null;
	}

	
	@Override
	public boolean isProtocol(ChannelContext channelContext){
		Object sessionContext = channelContext.getAttribute();
		if(sessionContext == null){
			return false;
		}else if(sessionContext instanceof WsSessionContext){
			return true;
		}
		return false;
	}


	@Override
	public String name() {
		
		return Protocol.WEBSOCKET;
	}

}