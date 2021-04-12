package com.tiny.push.push.bean;

/**
 * 推送
 * @author wangwei
 * @date 2020/4/27 9:54:18
 */
public class AndroidPushBody extends PushBody {

    private String ticker;

    private String title;

    private String text;

    private String sound;

    private CustomBody custom;

    @Override
    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public CustomBody getCustom() {
        return custom;
    }

    public void setCustom(CustomBody custom) {
        this.custom = custom;
    }

    @Override
    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }
}
