package com.tiny.push.push.bean;

/**
 * 推送
 * @author wangwei
 * @date 2020/4/27 9:54:18
 */
public class PushResponse {

    private String ret;

    private PushResponseData data;

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public PushResponseData getData() {
        return data;
    }

    public void setData(PushResponseData data) {
        this.data = data;
    }
}
