package com.tiny.push.push.bean;

/**
 * 推送
 * @author wangwei
 * @date 2020/4/27 9:54:18
 */
public class IosPushBody extends PushBody {

    private AlertBody alert;

    private String sound;

    @Override
    public AlertBody getAlert() {
        return alert;
    }

    public void setAlert(AlertBody alert) {
        this.alert = alert;
    }

    @Override
    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }
}
