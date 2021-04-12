package com.tiny.chat.signal;

import com.tiny.common.packet.ImPacket;
import com.tiny.common.packet.Signal;
import org.tio.core.ChannelContext;

/**
 * @author way
 */
public interface SignalHandler {

    /**
     * 主信令
     * @return
     */
    public Signal signal();

    /**
     *
     * @param packet
     * @param channelContext
     * @return
     * @throws Exception
     */
    public ImPacket handler(ImPacket packet, ChannelContext channelContext)  throws Exception;
}
