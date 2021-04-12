package com.tiny.chat.proess;

import com.tiny.common.packet.ImPacket;
import com.tiny.common.packet.Signal;
import com.tiny.chat.annotation.Handler;
import com.tiny.chat.signal.AbstractSignalHandler;
import org.tio.core.ChannelContext;

@Handler("default")
public class DefaultHandler extends AbstractSignalHandler {


    @Override
    public Signal signal() {
        return null;
    }

    @Override
    public ImPacket handler(ImPacket packet, ChannelContext channelContext) throws Exception {
        return null;
    }
}
