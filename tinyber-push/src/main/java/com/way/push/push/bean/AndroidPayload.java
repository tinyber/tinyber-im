package com.tiny.push.push.bean;

/**
 * 推送
 * @author wangwei
 * @date 2020/4/27 9:54:18
 */
public class AndroidPayload extends PushPayload {

    private String display_type;

    private PushBody body;

    public AndroidPayload() {
    }

    public AndroidPayload(String display_type, PushBody body) {
        this.display_type = display_type;
        this.body = body;
    }

    public String getDisplay_type() {
        return display_type;
    }

    public void setDisplay_type(String display_type) {
        this.display_type = display_type;
    }

    public PushBody getBody() {
        return body;
    }

    public void setBody(PushBody body) {
        this.body = body;
    }
}
