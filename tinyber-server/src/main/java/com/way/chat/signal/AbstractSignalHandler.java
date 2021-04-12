package com.tiny.chat.signal;

import com.tiny.chat.proess.ProcessorIntf;

import java.util.HashMap;
import java.util.Map;

/**
 * @author by wangwei
 * @ClassName AbstractServerHandler
 * @Description 封装tioServerAioHandler，提供更丰富的方法供客户端定制化;
 * @Date 2020/11/4 14:16
 */
public abstract class AbstractSignalHandler implements SignalHandler {

    //不同协议cmd处理命令如(ws、socket、自定义协议)握手、心跳命令等.
    protected Map<String, ProcessorIntf> processors = new HashMap<String,ProcessorIntf>();

    /**
     * 协议名称
     * @return
     */
    //public abstract String name();

    /**
     * 初始化
     * @param tioConfig
     * @throws Exception
     */
    //public abstract void init(TioConfig tioConfig)throws Exception;

    /**
     *协议
     * @param byteBuffer
     * @param channelContext
     * @return
     * @throws Throwable
     */
    //public abstract boolean isProtocol(ByteBuffer byteBuffer, ChannelContext channelContext)throws Throwable;


}
