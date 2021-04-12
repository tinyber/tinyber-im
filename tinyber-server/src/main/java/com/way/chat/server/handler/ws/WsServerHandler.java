/**
 * 
 */
package com.tiny.chat.server.handler.ws;

import com.tiny.chat.manager.SignalHandlerManager;
import com.tiny.chat.server.handler.AbstractServerHandler;
import com.tiny.chat.signal.AbstractSignalHandler;
import com.tiny.common.ImServerApi;
import com.tiny.common.constants.enums.ImStatusEnum;
import com.tiny.common.packet.ImPacket;
import com.tiny.common.packet.Signal;
import com.tiny.common.packet.http.HttpRequest;
import com.tiny.common.packet.http.HttpRequestDecoder;
import com.tiny.common.packet.http.HttpResponse;
import com.tiny.common.packet.http.HttpResponseEncoder;
import com.tiny.common.packet.ws.*;
import com.tiny.common.response.RespBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.exception.AioDecodeException;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Objects;

/**
 * 版本: [1.0]
 * 功能说明: 
 * @author : WChao 创建时间: 2017年8月3日 下午6:38:36
 */
public class WsServerHandler extends AbstractServerHandler {
	
	private Logger logger = LoggerFactory.getLogger(WsServerHandler.class);
	
	private WsConfig wsServerConfig;

	private IWsMsgHandler wsMsgHandler;
	
	public WsServerHandler() {
		this.protocol = new WsProtocol(new WsConvertPacket());
	}
	
	public WsServerHandler(WsConfig wsServerConfig, AbstractProtocol protocol) {
		super(protocol);
		this.wsServerConfig = wsServerConfig;
	}
	@Override
	public void init(ImServerConfig imServerConfig) {
		WsConfig wsConfig = imServerConfig.getWsConfig();
		if(Objects.isNull(wsConfig)){
			wsConfig = WsConfig.newBuilder().build();
			imServerConfig.setWsConfig(wsConfig);
		}
		IWsMsgHandler wsMsgHandler = wsConfig.getWsMsgHandler();
		if(Objects.isNull(wsMsgHandler)){
			wsConfig.setWsMsgHandler(new WsMsgHandler());
		}
		this.wsServerConfig = wsConfig;
		this.wsMsgHandler = wsServerConfig.getWsMsgHandler();
		logger.info("WebSocket Protocol  initialized");
	}

	@Override
	public ByteBuffer encode(ImPacket imPacket, ImConfig imConfig, ChannelContext channelContext) throws UnsupportedEncodingException {
		WsSessionContext wsSessionContext = (WsSessionContext)channelContext.get();
		WsResponsePacket wsResponsePacket = (WsResponsePacket)imPacket;
		if (wsResponsePacket.getSignal() == Signal.PING) {
			//握手包
			HttpResponse handshakeResponsePacket = wsSessionContext.getHandshakeResponse();
			return HttpResponseEncoder.encode(handshakeResponsePacket, channelContext.tioConfig,channelContext);
		}else{
			return WsServerEncoder.encode(wsResponsePacket , channelContext.getTioConfig(),channelContext);
		}
	}

	@Override
	public void handler(ImPacket imPacket, ChannelContext channelContext) throws Exception {
		WsRequestPacket wsRequestPacket = (WsRequestPacket) imPacket;
		AbstractSignalHandler serverHandler = SignalHandlerManager.getSignal(wsRequestPacket.getSignal());
		if(serverHandler == null){
			//是否ws分片发包尾帧包
			if(!wsRequestPacket.isWsEof()) {
				return;
			}
			ImPacket wsPacket = new ImPacket(Signal.NONE, new RespBody(Signal.NONE, ImStatusEnum.C10017).toByte());
			ImServerApi.send(channelContext, wsPacket);
			return;
		}
		ImPacket response = serverHandler.handler(wsRequestPacket, channelContext);
		if(Objects.nonNull(response)){
			ImServerApi.send(channelContext, response);
		}
	}

	@Override
	public ImPacket decode(ByteBuffer buffer, int limit, int position, int readableLength, ChannelContext channelContext) throws AioDecodeException {
		WsSessionContext wsSessionContext = (WsSessionContext)channelContext.get();
		//握手
		if(!wsSessionContext.isHandshaked()){
			HttpRequest httpRequest = HttpRequestDecoder.decode(buffer,limit,position,readableLength,channelContext,true);
			if(httpRequest == null) {
				return null;
			}
			//升级到WebSocket协议处理
			HttpResponse httpResponse = WsServerDecoder.updateWebSocketProtocol(httpRequest, channelContext);
			if (httpResponse == null) {
				throw new SecurityException("http协议升级到webSocket协议失败");
			}
			wsSessionContext.setHandshakeRequest(httpRequest);
			wsSessionContext.setHandshakeResponse(httpResponse);

			WsRequestPacket wsRequestPacket = new WsRequestPacket();
			wsRequestPacket.setHandShake(true);
			wsRequestPacket.setSignal(Signal.PING);
			return wsRequestPacket;
		}else{
			WsRequest wsRequestPacket = WsServerDecoder.decode(buffer, channelContext);
			if(wsRequestPacket == null) {
				return null;
			}
			Signal signal = null;
			if(wsRequestPacket.getWsOpcode() == Opcode.CLOSE){
				signal = Signal.UN_CONNECT;
			}else{
				/*try{
					Message message = JsonKit.toBean(wsRequestPacket.getBody(),Message.class);
					command = Command.forNumber(message.getCmd());
				}catch(Exception e){
					return wsRequestPacket;
				}*/
			}
			wsRequestPacket.setSignal(signal);
			return wsRequestPacket;
		}
	}
	public WsConfig getWsServerConfig() {
		return wsServerConfig;
	}

	public void setWsServerConfig(WsConfig wsServerConfig) {
		this.wsServerConfig = wsServerConfig;
	}

	public IWsMsgHandler getWsMsgHandler() {
		return wsMsgHandler;
	}

	public void setWsMsgHandler(IWsMsgHandler wsMsgHandler) {
		this.wsMsgHandler = wsMsgHandler;
	}

}
