package com.tiny.common.packet;

import org.tio.core.ChannelContext;
import org.tio.monitor.RateLimiterWrap;
import org.tio.server.intf.ServerAioHandler;

/**
 * @author way
 */
public class ImSessionContext extends SessionContext {

    /**
     * 消息请求频率控制器
     */
    private RateLimiterWrap requestRateLimiter = null;

    /**
     * 通道所属协议处理器;
     */
    private ServerAioHandler serverHandler;

    private Client client;

    private ChannelContext channelContext;

    public Client getClient() {
        return client;
    }

    public ChannelContext getChannelContext() {
        return channelContext;
    }

    public void setChannelContext(ChannelContext channelContext) {
        this.channelContext = channelContext;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public RateLimiterWrap getRequestRateLimiter() {
        return requestRateLimiter;
    }

    public void setRequestRateLimiter(RateLimiterWrap requestRateLimiter) {
        this.requestRateLimiter = requestRateLimiter;
    }

    public ServerAioHandler getServerHandler() {
        return serverHandler;
    }

    public ImSessionContext setServerHandler(ServerAioHandler serverHandler) {
        this.serverHandler = serverHandler;
        return this;
    }
}
