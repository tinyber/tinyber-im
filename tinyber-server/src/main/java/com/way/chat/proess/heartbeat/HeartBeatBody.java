package com.tiny.chat.proess.heartbeat;

/**
 * 心跳
 */
public class HeartBeatBody {

    long interval;

    public long getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
}
