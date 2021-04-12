package com.tiny.common.constants.enums;

public enum ImStatusEnum {

	C10000(10000,"ok","发送成功"),
	C10001(10001,"offline","用户不在线"),
	C10002(10002,"send failed","消息发送失败,数据格式不正确,请参考:{'from':来源ID,'to':目标ID,'cmd':'消息命令码','createTime':消息创建时间(Long型),'msgType':Integer消息类型,'content':内容}"),
	C10003(10003,"ok","获取用户信息成功!"),
	C10004(10004,"get user failed !","获取用户信息失败!"),
	C10005(10005,"ok","获取在线用户信息成功!"),
	C10006(10006,"ok","获取所有用户信息成功!"),
	C10007(10007,"ok","登录成功!"),
	C10008(10008,"login failed !","登录失败!"),
	C10009(10009,"ok","鉴权成功!"),
	C10010(10010,"auth failed!","鉴权失败!"),
	C10011(10011,"join group ok!","加入群组成功!"),
	C10012(10012,"join group failed!","加入群组失败!"),
	C10013(10013,"Protocol version number does not match","协议版本号不匹配!"),
	C10014(10014,"unsupported cmd command","不支持的cmd命令!");

	public static final Object C10017 = "";
	private int status;

	private String description;

	private String text;

	private ImStatusEnum(int status, String description, String text) {
		this.status = status;
		this.description = description;
		this.text = text;
	}

	public int getStatus() {
		return status;
	}

	public String getDescription() {
		return description;
	}

	public String getText() {
		return text;
	}

	public int getCode() {
		return this.status;
	}

	public String getMsg() {
		return this.getStatus()+" "+this.getDescription()+" "+this.getText();
	}
}
