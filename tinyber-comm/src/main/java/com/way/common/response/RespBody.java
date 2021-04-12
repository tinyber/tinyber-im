package com.tiny.common.response;

import com.tiny.common.packet.Signal;
import com.tiny.common.util.JsonUtil;

import java.util.Objects;

public class RespBody{

	private Integer code;//响应状态码;

	private String msg;//响应状态信息提示;

	private Signal signal;//响应cmd命令码;

	private Object data;//响应数据;

	public RespBody(){}
	public RespBody(Signal signal){
		this.signal = signal;
	}
	public RespBody(Signal signal,Object data){
		this(signal);
		this.data = data;
	}
	public Integer getCode() {
		return code;
	}

	public RespBody setCode(Integer code) {
		this.code = code;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public RespBody setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public Object getData() {
		return data;
	}
	public RespBody setData(Object data) {
		this.data = data;
		return this;
	}
	@Override
	public String toString() {
		return JsonUtil.toJson(this);
	}

	public byte[] toByte(){
		return Objects.requireNonNull(JsonUtil.toJson(this)).getBytes();
	}

}
