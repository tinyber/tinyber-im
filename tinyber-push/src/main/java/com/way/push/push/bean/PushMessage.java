package com.tiny.push.push.bean;


/**
 * 推送
 * @author wangwei
 * @date 2020/4/27 9:54:18
 */
public abstract class PushMessage {

    /**
     * 应用唯一标识
     */
    private String appkey;

    /**
     * 时间戳，10位或者13位均可，时间戳有效期为10分钟
     */
    private long timestamp;

    /**
     * 消息发送类型,其值可以为单播 广播 组播
     */
    private String type;

    /**
     * 单个设备
     */
    private String device_tokens;

    /**
     * 正式/测试模式。默认为true
     */
    private boolean production_mode;

    /**
     * 发送消息描述，建议填写
     */
    private String description;



    public PushMessage() {

    }

    public PushMessage(String appkey, long timestamp, String type, String device_tokens, boolean production_mode) {
        this.appkey = appkey;
        this.timestamp = timestamp;
        this.type = type;
        this.device_tokens = device_tokens;
        this.production_mode = production_mode;
    }

    public abstract PushPayload getPayload();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDevice_tokens() {
        return device_tokens;
    }

    public void setDevice_tokens(String device_tokens) {
        this.device_tokens = device_tokens;
    }

    public boolean isProduction_mode() {
        return production_mode;
    }

    public void setProduction_mode(boolean production_mode) {
        this.production_mode = production_mode;
    }
}
