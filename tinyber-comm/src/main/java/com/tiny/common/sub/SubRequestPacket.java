package com.tiny.common.sub;

import com.tiny.common.packet.Header;
import com.tiny.common.packet.ImPacket;
import com.tiny.common.packet.Signal;

import java.nio.ByteBuffer;

public class SubRequestPacket extends ImPacket {

    @Override
    public ByteBuffer encode() {
        ByteBuffer buffer = ByteBuffer.allocate(Header.LENGTH);
        Header header = new Header();
        header.setSignal(Signal.SUB);
        header.setLength(0);
        buffer.put(header.getContents());
        return buffer;
    }
}
