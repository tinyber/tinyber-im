package com.tiny.chat.proess.in;

import com.tiny.common.packet.ImPacket;
import com.tiny.chat.proess.ProcessorIntf;
import org.tio.core.ChannelContext;

public interface HandshakeProcessorIntf extends ProcessorIntf {

	ImPacket handshake(ImPacket packet, ChannelContext channelContext)  throws Exception;
}
