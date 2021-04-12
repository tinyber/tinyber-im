package com.tiny.chat.proess;

import com.tiny.common.packet.ImPacket;
import com.tiny.common.packet.Signal;
import com.tiny.common.packet.SubSignal;
import com.tiny.chat.annotation.Handler;
import com.tiny.chat.proess.heartbeat.HeartbeatRequestPacket;
import com.tiny.chat.signal.AbstractSignalHandler;
import org.tio.core.ChannelContext;

@Handler("heartBeat")
public class HeartBeatHandler extends AbstractSignalHandler {

    @Override
    public Signal signal() {
        return Signal.PING;
    }

    @Override
    public ImPacket handler(ImPacket packet, ChannelContext channelContext) throws Exception {
        HeartbeatRequestPacket imPacket=(HeartbeatRequestPacket) packet;
        SubSignal subSignal = imPacket.getHeader().getSubSignal();
        return null;
    }
}
