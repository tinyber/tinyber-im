package com.tiny.chat.proess.heartbeat;


import com.tiny.common.packet.ImPacket;
import com.tiny.common.packet.Signal;

import java.nio.charset.StandardCharsets;


/**
 * 心跳返回包
 */
public class HeartbeatResponsePacket extends ImPacket {

    public HeartbeatResponsePacket(String heartBeat) {
        this.setBody(heartBeat.getBytes(StandardCharsets.UTF_8));
    }

    public Signal signal() {
        return Signal.PING;
    }
}
