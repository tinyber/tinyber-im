package com.tiny.chat.proess.heartbeat;


import com.tiny.common.packet.Header;
import com.tiny.common.packet.ImPacket;
import com.tiny.common.packet.Signal;

import java.nio.ByteBuffer;

/**
 * 心跳请求包
 */
public class HeartbeatRequestPacket extends ImPacket {

    @Override
    public ByteBuffer encode() {
        int bodyLength = 0;
        if (getBody() != null) {
            bodyLength = getBody().length;
        }
        ByteBuffer buffer = ByteBuffer.allocate(Header.LENGTH + bodyLength);
        Header header = new Header();
        header.setSignal(Signal.PING);
        header.setLength(bodyLength);
        buffer.put(header.getContents());
        if (getBody() != null) {
            buffer.put(getBody());
        }
        return buffer;
    }
}
