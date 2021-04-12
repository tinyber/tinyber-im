package com.tiny.common.chat;

/**
 * @author Administrator
 */
public class ConnectRequest {

    /**
     * 账户
     */
    private String account;

    /**
     * 渠道
     */
    private String channel="browser";

    /**
     * app版本
     */
    private String appVersion="1.0.0";

    /**
     * 操作系统版本 浏览器版本
     */
    private String osVersion="83";

    /**
     * 包名
     */
    private String packageName="com.gz.gim";

    /**
     * 设备ID 自定义生成
     */
    private String deviceId="";

    /**
     * 设备
     */
    private String device="chrome";

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
