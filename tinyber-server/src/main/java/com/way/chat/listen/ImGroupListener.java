package com.tiny.chat.listen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.intf.GroupListener;

public class ImGroupListener implements GroupListener {

    private static final Logger log = LoggerFactory.getLogger(ImGroupListener.class);


    @Override
    public void onAfterBind(ChannelContext channelContext, String group) throws Exception {
        if (log.isInfoEnabled()) {
           // log.info("onAfterBind\r\n{}", Json.toFormatedJson(group));
        }
    }

    @Override
    public void onAfterUnbind(ChannelContext channelContext, String s) throws Exception {

    }
}
