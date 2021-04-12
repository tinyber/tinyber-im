package com.tiny.common;

import com.tiny.common.config.ImConfig;
import com.tiny.common.packet.ImPacket;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;

public class ImServerApi {

    private ImConfig imConfig;


    public static void send(ChannelContext channelContext, ImPacket wsPacket) {

        Tio.send(channelContext,wsPacket);
    }
}
