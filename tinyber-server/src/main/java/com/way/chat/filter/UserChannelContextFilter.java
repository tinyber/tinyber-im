package com.tiny.chat.filter;

import org.tio.core.ChannelContext;
import org.tio.core.ChannelContextFilter;

/**
 * @author by wangwei
 * @ClassName UserChannelContextFilter
 * @Description TODO
 * @Date 2020/10/19 11:18
 */
public class UserChannelContextFilter implements ChannelContextFilter {

    private ChannelContext channelContext;

    public ChannelContext getChannelContext() {
        return channelContext;
    }

    public void removeChannelContext() {
        this.channelContext = null;
    }

    public UserChannelContextFilter(ChannelContext channelContext) {
        this.channelContext = channelContext;
    }

    @Override
    public boolean filter(ChannelContext channelContext) {
        return this.channelContext != channelContext;
    }
}
