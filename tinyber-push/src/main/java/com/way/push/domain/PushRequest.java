package com.tiny.push.domain;

/**
 * @author by wangwei
 * @ClassName PushRequest
 * @Description TODO
 * @Date 2020/7/9 14:45
 */
public class PushRequest {

    /**
     *
     */
    private Integer platform;

    /**
     * 设备类型
     */
    private String deviceType;

    /**
     * 设备token
     */
    private String deviceToken;

    /**
     * voipToken
     */
    private String voipDeviceToken;
    /**
     * 标题
     */
    private String title;

    /**
     * 副标题
     */
    private String subTitle;

    /**
     * 消息
     */
    private String message;

    /**
     * 消息的类型，普通消息通知栏；voip要透传。
     */
    private int pushMessageType;

    private String alias;

    private String aliasType;

    /**
     * 声音
     */
    private String sound;

    /**
     * 安卓单消息
     */
    private String custom;

    /**
     * 消息类型: notification(通知)、message(消息)
     */
    private String displayType;

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getVoipDeviceToken() {
        return voipDeviceToken;
    }

    public void setVoipDeviceToken(String voipDeviceToken) {
        this.voipDeviceToken = voipDeviceToken;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPushMessageType() {
        return pushMessageType;
    }

    public void setPushMessageType(int pushMessageType) {
        this.pushMessageType = pushMessageType;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAliasType() {
        return aliasType;
    }

    public void setAliasType(String aliasType) {
        this.aliasType = aliasType;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }
}
