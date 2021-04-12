package com.tiny.common.util;

import com.tiny.common.Protocol;
import com.tiny.common.packet.ImPacket;
import com.tiny.common.packet.http.HttpConst;
import com.tiny.common.packet.http.HttpRequest;
import com.tiny.common.packet.http.HttpResponse;
import com.tiny.common.packet.http.HttpSession;
import com.tiny.common.packet.tcp.TcpPacket;
import com.tiny.common.packet.tcp.TcpServerEncoder;
import com.tiny.common.packet.tcp.TcpSessionContext;
import com.tiny.common.packet.ws.Opcode;
import com.tiny.common.packet.ws.WsResponsePacket;
import com.tiny.common.packet.ws.WsSessionContext;
import com.tiny.common.response.RespBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;

public class ImKit {

    private static final Logger logger = LoggerFactory.getLogger(ImKit.class);

    /**
     * 功能描述：[转换不同协议响应包]
     * 创建者：WChao 创建时间: 2017年9月21日 下午3:21:54
     * @param signal
     * @param channelContext
     * @return
     */
    public static ImPacket convertRespPacket(RespBody respBody, ChannelContext channelContext) {
        ImPacket imPacket = null;
        if (respBody == null) {
            return imPacket;
        }
        byte[] body;
        try {
            body = respBody.toString().getBytes(HttpConst.CHARSET_NAME);
			imPacket = convertRespPacket(body, respBody, channelContext);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return imPacket;
    }

    /**
     * 功能描述：[转换不同协议响应包]
     * 创建者：WChao 创建时间: 2017年9月21日 下午3:21:54
     * @param body
     * @param channelContext
     * @return
     */
    public static ImPacket convertRespPacket(byte[] body, RespBody respBody, ChannelContext channelContext) {
        Object sessionContext = channelContext.get();
        ImPacket imPacket = null;
        //转HTTP协议响应包;
        if (sessionContext instanceof HttpSession) {
            HttpRequest request = (HttpRequest) channelContext.getAttribute("httpRequest");
            HttpResponse response = new HttpResponse(null, body);
            response.setBody(body);
			imPacket = response;
            //转TCP协议响应包;
        } else if (sessionContext instanceof TcpSessionContext) {
            TcpPacket tcpPacket = new TcpPacket(body);
            TcpServerEncoder.encode(tcpPacket, channelContext.getTioConfig(), channelContext);
			imPacket = tcpPacket;
            //转ws协议响应包;
        } else if (sessionContext instanceof WsSessionContext) {
            WsResponsePacket wsResponsePacket = new WsResponsePacket();
            wsResponsePacket.setBody(body);
            wsResponsePacket.setWsOpcode(Opcode.TEXT);
			imPacket = wsResponsePacket;
        }
		assert imPacket != null;
		//imPacket.setSignal(signal);
        return imPacket;
    }

    /**
     * 获取所属终端协议;
     *
     * @param channelContext
     * @return
     */
    public static String getTerminal(ChannelContext channelContext) {
        Object sessionContext = channelContext.get();
        //HTTP协议;
        if (sessionContext instanceof HttpSession) {
            return Protocol.HTTP;
            //TCP协议;
        } else if (sessionContext instanceof TcpSessionContext) {
            return Protocol.TCP;
            //ws协议;
        } else if (sessionContext instanceof WsSessionContext) {
            return Protocol.WEBSOCKET;
        }
        return "";
    }
}
