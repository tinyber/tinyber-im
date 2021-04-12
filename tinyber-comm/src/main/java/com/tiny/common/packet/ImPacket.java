package com.tiny.common.packet;


import com.tiny.common.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.core.exception.AioDecodeException;
import org.tio.core.intf.Packet;

import java.nio.ByteBuffer;

/**
 * @author by wangwei
 * @ClassName ImPacket消息对象
 * @Description TODO
 * @Date 2020/11/4 14:12
 */
public class ImPacket extends Packet {

    private static final Logger logger = LoggerFactory.getLogger(ImPacket.class);

    /**
     * 消息头的长度
     */
    public static final int HEADER_LENGTH = 4;

    public static final String CHARSET = "utf-8";

    private Header header;

    private byte[] body;

    public Signal signal;

    public SubSignal subSignal;

    public ImPacket(Signal signal, byte[] toByte) {
        this.signal = signal;
        this.body = toByte;
    }

    public ImPacket() {
    }

    public ImPacket(byte[] toByte) {
        this.body = toByte;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public byte[] getBody() {
        return body;
    }

    public int messageId() {
        return 0;
    }

    public void setBody(byte[] body) {
        this.body = body;
        if (body != null) {
            setByteCount(body.length);
        }
    }

    public Signal getSignal() {
        return signal;
    }

    public void setSignal(Signal signal) {
        this.signal = signal;
    }

    public SubSignal getSubSignal() {
        return subSignal;
    }

    public void setSubSignal(SubSignal subSignal) {
        this.subSignal = subSignal;
    }

    public void setBody(byte content) {
        this.body = new byte[1];
        body[0] = content;
        setByteCount(1);
    }

    public ByteBuffer encode() {
        int bodyLength = 0;
        if (getBody() != null) {
            bodyLength = getBody().length;
        }
        ByteBuffer buffer = ByteBuffer.allocate(Header.LENGTH + bodyLength);
        Header header = new Header();
        header.setSignal(getSignal());
        header.setSubSignal(getSubSignal());
        header.setMessageId(messageId());
        header.setLength(bodyLength);
        buffer.put(header.getContents());
        if (getBody() != null) {
            buffer.put(getBody());
        }
        return buffer;
    }

    public ImPacket decode(ByteBuffer byteBuffer, int readableLength, ChannelContext channelContext) throws AioDecodeException {
        if (readableLength < Header.LENGTH) {
            return null;
        }

        Header header = new Header(byteBuffer);
        if (!header.isValid()) {
            //如果是非法信息，则直接关闭链接
            Tio.close(channelContext, "close by invalid signal");
        }
        setHeader(header);
        //读取消息体的长度
        int bodyLength = header.getLength();

        if (bodyLength < 0) {
            throw new AioDecodeException("bodyLength [" + bodyLength + "] is not right,");
        }

        //计算本次需要的数据长度
        int neededLength = Header.LENGTH + bodyLength;
        //收到的数据是否足够组包
        int isDataEnough = readableLength - neededLength;
        logger.info("readableLength " + readableLength + " neededLength " + neededLength + " isDataEnough " + isDataEnough);
        if (isDataEnough < 0) {
            return null;
        }

        if (header.getLength() > 0) {
            //消息体大小
            byte[] body = new byte[header.getLength()];
            byteBuffer.get(body);
            setBody(body);
        }

        return this;
    }

    public static byte encodeEncrypt(byte bs, boolean isEncrypt) {
        if (isEncrypt) {
            return (byte) (bs | Protocol.FIRST_BYTE_MASK_ENCRYPT);
        } else {
            return (byte) (Protocol.FIRST_BYTE_MASK_ENCRYPT & 0b01111111);
        }
    }

    public static boolean decodeCompress(byte version) {
        return (Protocol.FIRST_BYTE_MASK_COMPRESS & version) != 0;
    }

    public static byte encodeCompress(byte bs, boolean isCompress) {
        if (isCompress) {
            return (byte) (bs | Protocol.FIRST_BYTE_MASK_COMPRESS);
        } else {
            return (byte) (bs & (Protocol.FIRST_BYTE_MASK_COMPRESS ^ 0b01111111));
        }
    }

    public static boolean decodeHasSynSeq(byte maskByte) {
        return (Protocol.FIRST_BYTE_MASK_HAS_SYNSEQ & maskByte) != 0;
    }

    public static byte encodeHasSynSeq(byte bs, boolean hasSynSeq) {
        if (hasSynSeq) {
            return (byte) (bs | Protocol.FIRST_BYTE_MASK_HAS_SYNSEQ);
        } else {
            return (byte) (bs & (Protocol.FIRST_BYTE_MASK_HAS_SYNSEQ ^ 0b01111111));
        }
    }

    public static boolean decode4ByteLength(byte version) {
        return (Protocol.FIRST_BYTE_MASK_4_BYTE_LENGTH & version) != 0;
    }

    public static byte encode4ByteLength(byte bs, boolean is4ByteLength) {
        if (is4ByteLength) {
            return (byte) (bs | Protocol.FIRST_BYTE_MASK_4_BYTE_LENGTH);
        } else {
            return (byte) (bs & (Protocol.FIRST_BYTE_MASK_4_BYTE_LENGTH ^ 0b01111111));
        }
    }

    public static byte decodeVersion(byte version) {
        return (byte) (Protocol.FIRST_BYTE_MASK_VERSION & version);
    }

    /**
     * 计算消息头占用了多少字节数
     *
     * @return
     * @author: wchao
     * 2017年1月31日 下午5:32:26
     */
    public int calcHeaderLength(boolean is4byteLength) {
        int ret = Protocol.LEAST_HEADER_LENGHT;
        if (is4byteLength) {
            ret += 2;
        }
        if (this.getSynSeq() > 0) {
            ret += 4;
        }
        return ret;
    }

}
