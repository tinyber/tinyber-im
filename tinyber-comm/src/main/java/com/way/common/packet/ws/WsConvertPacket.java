/**
 * 
 */
package com.tiny.common.packet.ws;

import com.tiny.common.packet.ImPacket;
import com.tiny.common.packet.ImSessionContext;
import com.tiny.common.packet.Signal;
import org.tio.core.ChannelContext;

/**
 * Ws协议消息转化包
 * @author WChao
 *
 */
public class WsConvertPacket{

	/**
	 * WebSocket响应包;
	 */
	public ImPacket RespPacket(byte[] body, Signal signal, ChannelContext channelContext) {
		ImSessionContext sessionContext = (ImSessionContext) channelContext.get();
		//转ws协议响应包;
		if(sessionContext instanceof WsSessionContext){
			WsResponsePacket wsResponsePacket = new WsResponsePacket();
			wsResponsePacket.setBody(body);
			wsResponsePacket.setWsOpcode(Opcode.TEXT);
			wsResponsePacket.setSignal(signal);
			return wsResponsePacket;
		}
		return null;
	}

	public ImPacket RespPacket(ImPacket imPacket,  Signal signal, ChannelContext channelContext) {

		return this.RespPacket(imPacket.getBody(), signal, channelContext);
	}

	public ImPacket ReqPacket(byte[] body, Signal signal, ChannelContext channelContext) {
		
		return null;
	}
}
