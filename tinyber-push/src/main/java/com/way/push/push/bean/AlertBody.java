package com.tiny.push.push.bean;


/**
 * 推送
 * @author wangwei
 * @date 2020/4/27 9:54:18
 */
public class AlertBody {

    private String title;

    private String subtitle;

    //private CustomBody body;

    private String body;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    /*public CustomBody getBody() {
        return body;
    }

    public void setBody(CustomBody body) {
        this.body = body;
    }*/
}
