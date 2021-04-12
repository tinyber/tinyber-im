package com.tiny.chat.proess.in;

import com.tiny.common.Protocol;
import com.tiny.common.packet.HandshakeBody;
import com.tiny.common.packet.ImPacket;
import com.tiny.common.packet.Signal;
import com.tiny.common.packet.tcp.TcpSessionContext;
import com.tiny.common.response.RespBody;
import com.tiny.common.util.ImKit;
import org.tio.core.ChannelContext;

public class TcpHandshakeProcessor implements HandshakeProcessorIntf {

	@Override
	public ImPacket handshake(ImPacket packet, ChannelContext channelContext) throws Exception {
		RespBody handshakeBody = new RespBody(Signal.PING,new HandshakeBody(Protocol.HANDSHAKE_BYTE));
		return ImKit.convertRespPacket(handshakeBody,channelContext);
	}


	@Override
	public boolean isProtocol(ChannelContext channelContext){
		Object sessionContext = channelContext.get();
		if(sessionContext == null){
			return false;
		}else if(sessionContext instanceof TcpSessionContext){
			return true;
		}
		return false;
	}


	@Override
	public String name() {

		return Protocol.TCP;
	}

}
