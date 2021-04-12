package com.tiny.chat.proess;

import com.tiny.common.packet.ImPacket;
import com.tiny.common.packet.Signal;
import com.tiny.chat.annotation.Handler;
import com.tiny.chat.signal.AbstractSignalHandler;
import org.tio.core.ChannelContext;

/**
 * 推送消息发送指令
 */
@Handler("push")
public class PushHandler extends AbstractSignalHandler {



    @Override
    public Signal signal() {
        return Signal.PUSH;
    }

    @Override
    public ImPacket handler(ImPacket packet, ChannelContext channelContext) throws Exception {
        return null;
    }
}
