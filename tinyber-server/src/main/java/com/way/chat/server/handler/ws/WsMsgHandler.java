package com.tiny.chat.server.handler.ws;

import com.tiny.chat.ws.WsServerConfig;
import com.tiny.common.chat.ChatBody;
import com.tiny.common.packet.ImPacket;
import com.tiny.common.packet.http.HttpConst;
import com.tiny.common.packet.http.HttpRequest;
import com.tiny.common.packet.http.HttpResponse;
import com.tiny.common.packet.ws.*;
import com.tiny.common.util.ChatKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.utils.lock.SetWithLock;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class WsMsgHandler implements IWsMsgHandler {

	private static final Logger log = LoggerFactory.getLogger(WsMsgHandler.class);


	private WsServerConfig wsServerConfig = null;

	public Object onText(WsRequestPacket wsRequestPacket, String text, ChannelContext channelContext) throws Exception {
		ChatBody chatBody = ChatKit.toChatBody(wsRequestPacket.getBody(), channelContext);
		SetWithLock<ChannelContext> toChannleContexts = Tio.getByUserid(channelContext.tioConfig,chatBody.getReceiver());
		if(toChannleContexts != null && toChannleContexts.size() > 0){
			Tio.send(channelContext, wsRequestPacket);
			ImPacket sendSuccessPacket  = ChatKit.sendSuccessRespPacket(channelContext);
			text = new String(sendSuccessPacket.getBody(), HttpConst.CHARSET_NAME);
		}else{
			ImPacket offlineRespPacket =ChatKit.offlineRespPacket(channelContext);
			text = new String(offlineRespPacket.getBody(),HttpConst.CHARSET_NAME);
		}
		return text;
	}

	/**
	 * 
	 * @param websocketPacket
	 * @param bytes
	 * @param channelContext
	 * @return 可以是WsResponsePacket、byte[]、ByteBuffer、null
	 * @author: WChao
	 */
	public Object onBytes(WsRequestPacket websocketPacket, byte[] bytes, ChannelContext channelContext) throws Exception {
		String text = new String(bytes, StandardCharsets.UTF_8);
		log.info("收到byte消息:{},{}", bytes, text);
		ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
		buffer.put(bytes);
		return buffer;
	}

	/** 
	 * @param imPacket
	 * @param channelContext
	 * @return
	 * @throws Exception
	 * @author: WChao
	 */
	public WsResponsePacket handler(ImPacket imPacket, ChannelContext channelContext)throws Exception {
		WsRequestPacket wsRequest = (WsRequestPacket)imPacket;
		return h(wsRequest, wsRequest.getBody(), wsRequest.getWsOpcode(), channelContext);
	}
	
	public WsResponsePacket h(WsRequestPacket wsRequest, byte[] bytes, Opcode opcode, ChannelContext channelContext) throws Exception {
		WsResponsePacket wsResponse = null;
		if (opcode == Opcode.TEXT) {
			if (bytes == null || bytes.length == 0) {
				Tio.remove(channelContext, "错误的websocket包，body为空");
				return null;
			}
			String text = new String(bytes, StandardCharsets.UTF_8);
			Object retObj = this.onText(wsRequest, text, channelContext);
			String methodName = "onText";
			wsResponse = processRetObj(retObj, methodName, channelContext);
			return wsResponse;
		} else if (opcode == Opcode.BINARY) {
			if (bytes == null || bytes.length == 0) {
				Tio.remove(channelContext, "错误的websocket包，body为空");
				return null;
			}
			Object retObj = this.onBytes(wsRequest, bytes, channelContext);
			String methodName = "onBytes";
			wsResponse = processRetObj(retObj, methodName, channelContext);
			return wsResponse;
		} else if (opcode == Opcode.PING || opcode == Opcode.PONG) {
			log.error("收到" + opcode);
			return null;
		} else if (opcode == Opcode.CLOSE) {
			Object retObj = this.onClose(wsRequest, bytes, channelContext);
			String methodName = "onClose";
			wsResponse = processRetObj(retObj, methodName, channelContext);
			return wsResponse;
		} else {
			Tio.remove(channelContext, "错误的websocket包，错误的Opcode");
			return null;
		}
	}

	private WsResponsePacket processRetObj(Object obj, String methodName, ChannelContext channelContext) throws Exception {
		WsResponsePacket wsResponse = null;
		if (obj == null) {
			return null;
		} else {
			if (obj instanceof String) {
				String str = (String) obj;
				wsResponse = new WsResponsePacket();
				wsResponse.setBody(str.getBytes(StandardCharsets.UTF_8));
				wsResponse.setWsOpcode(Opcode.TEXT);
				return wsResponse;
			} else if (obj instanceof byte[]) {
				wsResponse = new WsResponsePacket();
				wsResponse.setBody((byte[]) obj);
				wsResponse.setWsOpcode(Opcode.BINARY);
				return wsResponse;
			} else if (obj instanceof WsResponsePacket) {
				return (WsResponsePacket) obj;
			} else if (obj instanceof ByteBuffer) {
				wsResponse = new WsResponsePacket();
				byte[] bs = ((ByteBuffer) obj).array();
				wsResponse.setBody(bs);
				wsResponse.setWsOpcode(Opcode.BINARY);
				return wsResponse;
			} else {
				log.error("{} {}.{}()方法，只允许返回byte[]、ByteBuffer、WebSocketResponsePacket或null，但是程序返回了{}", channelContext, this.getClass().getName(), methodName, obj.getClass().getName());
				return null;
			}
		}
		
	}

	@Override
	public HttpResponse handshake(HttpRequest httpRequest, HttpResponse httpResponse, ChannelContext channelContext) throws Exception {
		return null;
	}

	@Override
	public void onAfterHandshaked(HttpRequest httpRequest, HttpResponse httpResponse, ChannelContext channelContext) throws Exception {

	}

	@Override
	public Object onBytes(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
		String text = new String(bytes, "utf-8");
		log.info("收到byte消息:{},{}", bytes, text);
		ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
		buffer.put(bytes);
		return buffer;
	}

	@Override
	public Object onClose(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
		return null;
	}

	public Object onClose(WsRequestPacket websocketPacket, byte[] bytes, ChannelContext channelContext) throws Exception {
		Tio.remove(channelContext, "receive close flag");
		return null;
	}

	@Override
	public Object onText(WsRequest wsRequest, String text, ChannelContext channelContext) throws Exception {
		ChatBody chatBody =ChatKit.toChatBody(wsRequest.getBody(), channelContext);
		SetWithLock<ChannelContext> toChannleContexts = Tio.getByUserid(channelContext.tioConfig,chatBody.getReceiver());
		if(toChannleContexts != null && toChannleContexts.size() > 0){
			Tio.send(channelContext, wsRequest);
			ImPacket sendSuccessPacket = ChatKit.sendSuccessRespPacket(channelContext);
			text = new String(sendSuccessPacket.getBody(), HttpConst.CHARSET_NAME);
		}else{
			ImPacket offlineRespPacket =ChatKit.offlineRespPacket(channelContext);
			text = new String(offlineRespPacket.getBody(),HttpConst.CHARSET_NAME);
		}
		return text;
	}

	/**
	 *
	 * @author: WChao
	 */
	public WsMsgHandler(WsServerConfig wsServerConfig, String[] scanPackages) {
		this.setWsServerConfig(wsServerConfig);
		//this.routes = new Routes(scanPackages);
	}
	public WsMsgHandler() {
		this(new WsServerConfig(0), null);
	}

	/**
	 * @return the wsServerConfig
	 */
	public WsServerConfig getWsServerConfig() {
		return wsServerConfig;
	}

	/**
	 * @param wsServerConfig the wsServerConfig to set
	 */
	public void setWsServerConfig(WsServerConfig wsServerConfig) {
		this.wsServerConfig = wsServerConfig;
	}
}