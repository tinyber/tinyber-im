package com.tiny.chat.server.handler.tcp;

import com.tiny.common.constants.enums.ImStatusEnum;
import com.tiny.common.Protocol;
import com.tiny.common.packet.ImPacket;
import com.tiny.common.packet.Signal;
import com.tiny.common.packet.tcp.TcpPacket;
import com.tiny.common.packet.tcp.TcpServerDecoder;
import com.tiny.common.packet.tcp.TcpServerEncoder;
import com.tiny.common.packet.tcp.TcpSessionContext;
import com.tiny.common.response.RespBody;
import com.tiny.common.util.ImUtils;
import com.tiny.chat.config.ImConfig;
import com.tiny.chat.handler.AbstractServerHandler;
import com.tiny.chat.manager.SignalHandlerManager;
import com.tiny.chat.signal.AbstractSignalHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.core.TioConfig;
import org.tio.core.exception.TioDecodeException;
import org.tio.core.intf.Packet;

import java.nio.ByteBuffer;

/**
 * @author wangwei
 */
public class TcpServerHandler extends AbstractServerHandler {

    private static final Logger logger = LoggerFactory.getLogger(TcpServerHandler.class);

    @Override
    public boolean isProtocol(ByteBuffer buffer, ChannelContext channelContext) {
        if (buffer != null) {
            //获取第一个字节协议版本号;
            byte version = buffer.get();
            //TCP协议
            if (version == Protocol.VERSION) {
                channelContext.set(new TcpSessionContext().setServerHandler(this));
                ImUtils.setClient(channelContext);
                return true;
            }
        }
        return false;
    }

    @Override
    public Packet decode(ByteBuffer buffer, int limit, int position, int readableLength, ChannelContext channelContext) throws TioDecodeException {
        return TcpServerDecoder.decode(buffer, channelContext);
    }

    @Override
    public ByteBuffer encode(Packet packet, TioConfig tioConfig, ChannelContext channelContext) {
        TcpPacket tcpPacket = (TcpPacket) packet;
        return TcpServerEncoder.encode(tcpPacket, tioConfig, channelContext);
    }

    @Override
    public void handler(Packet packet, ChannelContext channelContext) throws Exception {
		TcpPacket tcpPacket = (TcpPacket) packet;
        AbstractSignalHandler cmdHandler = SignalHandlerManager.getSignal(tcpPacket.getSignal());
        if (cmdHandler == null) {
            ImPacket imPacket = new ImPacket(Signal.NONE, new RespBody(Signal.NONE, ImStatusEnum.C10014).toByte());
            Tio.send(channelContext, imPacket);
            return;
        }
        Object response = cmdHandler.handler(tcpPacket, channelContext);
        if (response != null && tcpPacket.getSynSeq() < 1) {
            Tio.send(channelContext, (ImPacket) response);
        }
    }

    public TcpPacket decode(ByteBuffer buffer, ChannelContext channelContext) throws TioDecodeException {
		TcpPacket tcpPacket = TcpServerDecoder.decode(buffer, channelContext);
        return tcpPacket;
    }

    @Override
    public String name() {

        return Protocol.TCP;
    }

    @Override
    public void init(ImConfig imConfig) throws Exception {

    }


}
