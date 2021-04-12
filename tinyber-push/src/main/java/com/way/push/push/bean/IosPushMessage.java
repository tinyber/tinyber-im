package com.tiny.push.push.bean;

/**
 * 推送
 * @author wangwei
 * @date 2020/4/27 9:54:18
 */
public class IosPushMessage extends PushMessage {

    private IosPayload iosPayload;

    public IosPushMessage() {
    }

    public IosPushMessage(String appkey, long timestamp, String type, String device_tokens, boolean production_mode, IosPayload iosPayload) {
        super(appkey, timestamp, type, device_tokens, production_mode);
        this.iosPayload = iosPayload;
    }

    public IosPushMessage(IosPayload iosPayload) {
        this.iosPayload = iosPayload;
    }

    @Override
    public PushPayload getPayload() {
        return iosPayload;
    }

    public void setIosPayload(IosPayload iosPayload) {
        this.iosPayload = iosPayload;
    }
}
