package com.tiny.chat.ws;

import com.tiny.chat.server.handler.AbstractServerHandler;
import com.tiny.chat.server.handler.ws.WsMsgHandler;
import com.tiny.common.constants.enums.ImStatusEnum;
import com.tiny.common.Protocol;
import com.tiny.common.chat.ChatBody;
import com.tiny.common.config.ImConfig;
import com.tiny.common.packet.ImPacket;
import com.tiny.common.packet.Signal;
import com.tiny.common.packet.http.*;
import com.tiny.common.packet.ws.*;
import com.tiny.common.response.RespBody;
import com.tiny.common.util.JsonUtil;
import com.tiny.chat.manager.SignalHandlerManager;
import com.tiny.chat.signal.AbstractSignalHandler;
import com.tiny.chat.util.PacketUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.core.TioConfig;
import org.tio.core.exception.TioDecodeException;
import org.tio.core.intf.Packet;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class WsServerHandler extends AbstractServerHandler {

	private static  final  Logger logger = LoggerFactory.getLogger(WsServerHandler.class);

	private WsServerConfig wsServerConfig;

	private IWsMsgHandler wsMsgHandler;

	public static final WsMsgHandler me = new WsMsgHandler();

	public WsServerHandler() {}

	public WsServerHandler(WsServerConfig wsServerConfig, IWsMsgHandler wsMsgHandler) {
		this.wsServerConfig = wsServerConfig;
		this.wsMsgHandler = wsMsgHandler;
	}

	@Override
	public boolean isProtocol(ByteBuffer buffer, ChannelContext channelContext)throws Throwable{
		//第一次连接;
		/*if(buffer != null){
			HttpRequest request = HttpRequestDecoder.decode(buffer, channelContext);
			assert request != null;
			if(request.getHeaders().get(HttpConst.RequestHeaderKey.Sec_WebSocket_Key) != null)
			{
				channelContext.setAttribute(new WsSessionContext().setServerHandler(this));
				ImUtils.setClient(channelContext);
				return true;
			}
		}*/
		return false;
	}

	@Override
	public Packet decode(ByteBuffer buffer, int limit, int position, int readableLength, ChannelContext channelContext) throws TioDecodeException {
		return PacketUtil.decode(buffer,limit,position,readableLength,channelContext);
	}

	@Override
	public ByteBuffer encode(Packet packet, TioConfig tioConfig, ChannelContext channelContext) {
		WsSessionContext wsSessionContext = (WsSessionContext)channelContext.get();
		WsResponsePacket wsResponsePacket = (WsResponsePacket)packet;
		if (wsResponsePacket.getSignal()== Signal.CONNECT) {
			//握手包
			HttpResponse handshakeResponsePacket = wsSessionContext.getHandshakeResponse();
			try {
				return HttpResponseEncoder.encode(handshakeResponsePacket, tioConfig, channelContext);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}else{
			return WsServerEncoder.encode(WsResponse.fromBytes(wsResponsePacket.getBody()) , tioConfig, channelContext);
		}
		return null;
	}

	@Override
	public void handler(Packet packet, ChannelContext channelContext) throws Exception {
		WsRequestPacket wsRequestPacket = (WsRequestPacket) packet;
		AbstractSignalHandler cmdHandler = SignalHandlerManager.getSignal(wsRequestPacket.getSignal());
		if(cmdHandler == null){
			//是否ws分片发包尾帧包
			if(!wsRequestPacket.isWsEof()) {
				return;
			}
			ImPacket imPacket = new ImPacket(Signal.NONE, new RespBody(Signal.NONE, ImStatusEnum.C10002).toByte());
			Tio.send(channelContext, imPacket);
			return;
		}
		Object response = cmdHandler.handler(wsRequestPacket, channelContext);
		if(response != null){
			Tio.send(channelContext, (ImPacket)response);
		}
	}

	public ImPacket decode(ByteBuffer buffer, ChannelContext channelContext) throws TioDecodeException {
		WsSessionContext wsSessionContext = (WsSessionContext)channelContext.get();
		//握手
		if(!wsSessionContext.isHandshaked()){
			HttpRequest  httpRequest =null; //HttpRequestDecoder.decode(buffer,channelContext);
			return null;
			//升级到WebSocket协议处理
		}else{
			WsRequest wsRequest = WsServerDecoder.decode(buffer, channelContext);
			if(wsRequest == null) {
				return null;
			}
			Signal signal = null;
			if(wsRequest.getWsOpcode() == Opcode.CLOSE){
				signal = Signal.DISCONNECT;
			}else{
				try{
					ChatBody message = JsonUtil.parse(wsRequest.getBody(),ChatBody.class);
					signal = Signal.forNumber(1);
				}catch(Exception e){
					return wsRequest;
				}
			}
			wsRequest.setSignal(signal);
			return wsRequest;
		}
	}
	public WsServerConfig getWsServerConfig() {
		return wsServerConfig;
	}

	public void setWsServerConfig(WsServerConfig wsServerConfig) {
		this.wsServerConfig = wsServerConfig;
	}

	public IWsMsgHandler getWsMsgHandler() {
		return wsMsgHandler;
	}

	public void setWsMsgHandler(IWsMsgHandler wsMsgHandler) {
		this.wsMsgHandler = wsMsgHandler;
	}

	@Override
	public String name() {

		return Protocol.WEBSOCKET;
	}
	@Override
	public void init(ImConfig imConfig) throws Exception {
		this.wsServerConfig = new WsServerConfig();
		imConfig.setWsServerConfig(wsServerConfig);
		this.wsMsgHandler = new WsMsgHandler();
		this.wsServerConfig.setWsMsgHandler(wsMsgHandler);
		logger.info("wsServerHandler 初始化完毕...");
	}
}
