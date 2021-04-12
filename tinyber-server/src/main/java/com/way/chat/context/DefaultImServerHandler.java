package com.tiny.chat.context;


import com.tiny.chat.server.handler.AbstractServerHandler;
import com.tiny.chat.server.handler.ServerHandlerManager;
import com.tiny.common.config.ImConfig;
import com.tiny.common.packet.ImPacket;
import org.tio.core.ChannelContext;

import java.nio.ByteBuffer;
import java.util.Objects;

/**
 * @ClassName DefaultImServerHandler
 * @Description TODO
 * @Author WChao
 * @Date 2020/1/6 2:25
 * @Version 1.0
 **/
public class DefaultImServerHandler implements ImServerHandler {
    /**
     * 处理消息包
     * @param imPacket
     * @param imChannelContext
     * @throws Exception
     */
    @Override
    public void handler(ImPacket imPacket, ChannelContext imChannelContext) throws ImException {
        ImServerChannelContext imServerChannelContext = (ImServerChannelContext)imChannelContext;
        AbstractServerHandler handler = imServerChannelContext.getProtocolHandler();
        if(Objects.isNull(handler)){
            return;
        }
        handler.handler(imPacket, imChannelContext);
    }

    /**
     * 编码
     * @param imPacket
     * @param imConfig
     * @param imChannelContext
     * @return
     */
    @Override
    public ByteBuffer encode(ImPacket imPacket, ImConfig imConfig, ImChannelContext imChannelContext) {
        ImServerChannelContext imServerChannelContext = (ImServerChannelContext)imChannelContext;
        AbstractServerHandler handler = imServerChannelContext.getProtocolHandler();
        if(handler != null){
            return handler.encode(imPacket, imConfig, imServerChannelContext);
        }
        return null;
    }

    /**
     * 解码
     * @param buffer
     * @param limit
     * @param position
     * @param readableLength
     * @param imChannelContext
     * @return
     * @throws ImDecodeException
     */
    @Override
    public ImPacket decode(ByteBuffer buffer, int limit, int position, int readableLength, ChannelContext imChannelContext){
        ImServerChannelContext imServerChannelContext = (ImServerChannelContext)imChannelContext;
        AbstractServerHandler handler = imChannelContext.getProtocolHandler();
        if(Objects.isNull(imServerChannelContext.getSessionContext())){
            handler = ServerHandlerManager.initServerHandlerToChannelContext(buffer, imChannelContext);
        }
        if(handler != null){
            return handler.decode(buffer, limit, position, readableLength, imChannelContext);
        }else{
            throw new ImDecodeException("unsupported protocol type, the protocol decoder cannot be found");
        }
    }
}
