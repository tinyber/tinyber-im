package com.tiny.chat.util;

import com.tiny.common.packet.ImPacket;
import org.tio.core.ChannelContext;
import org.tio.core.TioConfig;
import org.tio.core.exception.TioDecodeException;
import org.tio.core.intf.Packet;

import java.nio.ByteBuffer;

/**
 * 消息处理工具类
 *
 * @author wangwei
 */
public class PacketUtil {


    /**
     * 消息解码
     *
     * @param buffer
     * @param limit
     * @param position
     * @param readableLength
     * @param channelContext
     * @return
     * @throws TioDecodeException
     */
    public static ImPacket decode(ByteBuffer buffer, int limit, int position, int readableLength,
                                        ChannelContext channelContext) throws TioDecodeException {
        //提醒：buffer的开始位置并不一定是0，应用需要从buffer.position()开始读取数据
        //收到的数据组不了业务包，则返回null以告诉框架数据不够
        if (readableLength < ImPacket.HEADER_LENGTH) {
            return null;
        }

        //读取消息体的长度
        int bodyLength = buffer.getInt();

        //数据不正确，则抛出AioDecodeException异常
        if (bodyLength < 0) {
            throw new TioDecodeException("bodyLength [" + bodyLength + "] is not right, remote:" + channelContext.getClientNode());
        }

        //计算本次需要的数据长度
        int neededLength = ImPacket.HEADER_LENGTH + bodyLength;
        //收到的数据是否足够组包
        int isDataEnough = readableLength - neededLength;
        // 不够消息体长度(剩下的buffe组不了消息体)
        if (isDataEnough < 0) {
            return null;
        } else //组包成功
        {
            ImPacket imPacket = new ImPacket();
            if (bodyLength > 0) {
                byte[] dst = new byte[bodyLength];
                buffer.get(dst);
                imPacket.setBody(dst);
            }
            return imPacket;
        }
    }
    /**
     * 消息编码
     *
     * @param packet
     * @param channelContext
     * @return
     */
    public static ByteBuffer encode(Packet packet, TioConfig tioConfig ,ChannelContext channelContext) {
        ImPacket imPacket = (ImPacket) packet;
        byte[] body = imPacket.getBody();
        int bodyLen = 0;
        if (body != null) {
            bodyLen = body.length;
        }
        //bytebuffer的总长度是 = 消息头的长度 + 消息体的长度
        int allLen = ImPacket.HEADER_LENGTH + bodyLen;
        //创建一个新的bytebuffer
        ByteBuffer buffer = ByteBuffer.allocate(allLen);
        //设置字节序
        buffer.order(tioConfig.getByteOrder());

        //写入消息头----消息头的内容就是消息体的长度
        buffer.putInt(bodyLen);

        //写入消息体
        if (body != null) {
            buffer.put(body);
        }
        return buffer;
    }

    /**
     * 消息转换
     * @param webSocketProtoMessage
     * @return
     */
    /*private ImPacket convert2ImPacket(WebSocketProtoMessage webSocketProtoMessage) {
        ImPacket pushPacket = new ImPacket();
        Header header = new Header();
        header.setSignal(Signal.toEnum(webSocketProtoMessage.getSignal()));
        header.setSubSignal(SubSignal.toEnum(webSocketProtoMessage.getSubSignal()));
        header.setMessageId(webSocketProtoMessage.getMessageId());
        int length = webSocketProtoMessage.getContent() != null ? webSocketProtoMessage.getContent().getBytes().length : 0;
        header.setLength(length);
        pushPacket.setHeader(header);
        if (length != 0) {
            pushPacket.setBody(WsMessageHandler.getInstance().handleRequest(header.getSignal(), header.getSubSignal(), webSocketProtoMessage.getContent()));
        }
        return pushPacket;
    }*/

}
