package com.tiny.common.packet;

import java.util.Objects;

/**
 * @author way
 */
public class Client{

	public transient static String CHANNEL_IOS = "ios";

	public transient static String CHANNEL_ANDROID = "android";

	public transient static String CHANNEL_WEB = "web";

	public transient static String CHANNEL_MINI = "mini";

	private String id;

	/**
	 * session绑定的用户账号
	 */
	private String account;

	/**
	 * 如果没登录过，则为null
	 */
	private User user;

	/**
	 * 客户端ID (设备号码+应用包名),ios为deviceToken
	 */
	private String deviceId;

	/**
	 * 客户端ip
	 */
	private String ip;

	/**
	 * 客户端port
	 */
	private int port;

	/**
	 * session绑定的服务器IP
	 */
	private String host;

	/**
	 * 终端设备类型 ios android web mini
	 */
	private String channel;

	/**
	 * 终端设备型号
	 */
	private String deviceModel;

	/**
	 * 终端应用版本
	 */
	private String clientVersion;

	/**
	 * 终端系统版本
	 */
	private String systemVersion;

	/**
	 * 登录时间
	 */
	private Long bindTime;

	/**
	 * 经度
	 */
	private Double longitude;

	/**
	 * 维度
	 */
	private Double latitude;

	/**
	 * 位置
	 */
	private String location;

	/**
	 * APNs推送状态
	 */
	private int apns;

	/**
	 * 状态
	 */
	private int state;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public String getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}

	public String getSystemVersion() {
		return systemVersion;
	}

	public void setSystemVersion(String systemVersion) {
		this.systemVersion = systemVersion;
	}

	public Long getBindTime() {
		return bindTime;
	}

	public void setBindTime(Long bindTime) {
		this.bindTime = bindTime;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getApns() {
		return apns;
	}

	public void setApns(int apns) {
		this.apns = apns;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public boolean isIosChannel() {
		return Objects.equals(channel, CHANNEL_IOS);
	}

	public boolean isAndroidChannel() {
		return Objects.equals(channel, CHANNEL_ANDROID);
	}

	public boolean isWebChannel() {
		return Objects.equals(channel, CHANNEL_WEB);
	}

	public boolean isMiniChannel() {
		return Objects.equals(channel, CHANNEL_MINI);
	}

}
