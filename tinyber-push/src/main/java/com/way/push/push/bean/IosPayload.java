package com.tiny.push.push.bean;


/**
 * 推送
 * @author wangwei
 * @date 2020/4/27 9:54:18
 */
public class IosPayload extends PushPayload {

    private IosPushBody aps;

    public PushBody getAps() {
        return aps;
    }

    public void setAps(IosPushBody aps) {
        this.aps = aps;
    }
}
