package com.tiny.chat.server.handler;

import com.tiny.common.config.ImConfig;
import org.tio.core.ChannelContext;
import org.tio.server.intf.ServerAioHandler;

import java.nio.ByteBuffer;

/**
 * @author Administrator
 */
public abstract  class AbstractServerHandler implements ServerAioHandler {

    public abstract String name();

    public abstract void init(ImConfig imConfig)throws Exception;

    public abstract boolean isProtocol(ByteBuffer byteBuffer, ChannelContext channelContext)throws Throwable;

}
