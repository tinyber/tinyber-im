package com.tiny.common.packet.tcp;

import com.tiny.common.packet.ImPacket;
import com.tiny.common.packet.Signal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wangwei
 */
public class TcpPacket extends ImPacket {

    private static final Logger logger = LoggerFactory.getLogger(TcpPacket.class);


    private byte version = 0;
    private byte mask = 0;

    public TcpPacket(){

    }

    public TcpPacket(Signal signal, byte[] toByte) {
        super(signal, toByte);
    }
    public TcpPacket(byte[] body){
        super(body);
    }
    public byte getVersion() {
        return version;
    }
    public void setVersion(byte version) {
        this.version = version;
    }
    public byte getMask() {
        return mask;
    }
    public void setMask(byte mask) {
        this.mask = mask;
    }
}
