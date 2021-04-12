package com.tiny.push.push.bean;

/**
 * 推送
 * @author wangwei
 * @date 2020/4/27 9:54:18
 */
public class AndroidPushMessage extends PushMessage {


    private String mipush;

    private String mi_activity;

    private AndroidPayload androidPayload;

    public AndroidPushMessage() {
    }

    public AndroidPushMessage(String mipush,String mi_activity,String appkey, long timestamp, String type, String device_tokens, boolean production_mode, AndroidPayload androidPayload) {
        super(appkey, timestamp, type, device_tokens, production_mode);
        this.mi_activity=mi_activity;
        this.mipush=mipush;
        this.androidPayload = androidPayload;
    }

    public AndroidPushMessage(AndroidPayload androidPayload) {
        this.androidPayload = androidPayload;
    }

    @Override
    public PushPayload getPayload() {
        return androidPayload;
    }

    public void setAndroidPayload(AndroidPayload androidPayload) {
        this.androidPayload = androidPayload;
    }


    public String getMipush() {
        return mipush;
    }

    public void setMipush(String mipush) {
        this.mipush = mipush;
    }

    public String getMi_activity() {
        return mi_activity;
    }

    public void setMi_activity(String mi_activity) {
        this.mi_activity = mi_activity;
    }
}
