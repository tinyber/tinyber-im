package com.tiny.common.sub;

import com.alibaba.fastjson.JSON;
import com.tiny.common.packet.Header;
import com.tiny.common.packet.ImPacket;
import com.tiny.common.packet.Signal;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class SubResponsePacket extends ImPacket {
    byte[] subResponseByte;

    public SubResponsePacket(SubResponse subResponse){
        String responseJson = JSON.toJSONString(subResponse);
        try {
            subResponseByte = responseJson.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ByteBuffer encode() {
        int bodyLength = subResponseByte.length;
        ByteBuffer buffer = ByteBuffer.allocate(Header.LENGTH + bodyLength);
        Header header = new Header();
        header.setSignal(Signal.SUB);
        header.setLength(bodyLength);
        buffer.put(header.getContents());
        buffer.put(subResponseByte);
        return buffer;
    }
}
