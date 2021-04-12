package com.tiny.common.packet;


import org.apache.commons.lang3.StringUtils;

/**
 * @author comsicne
 * Copyright (c) [2019]
 * @Time 19-6-11 下午4:07
 **/
public enum SubSignal {
    /**
     * 默认
     */
    NONE,
    /**
     * 消息发送
     */
    MS,
    /**
     * 消息拉取
     */
    MP,
    /**
     * 预约推送
     */
    MR,

    /**
     * 发送回执
     */
    MC,
    /**
     * 系统模板消息
     */
    SYS,
    /**
     *
     */
    MULTIPLE,

    SUCCESS,

    FAILURE,
    ;

    public static SubSignal toEnum(int ordinal) {
        byte o = (byte) ordinal;
        if (o > NONE.ordinal() &&
                o < SubSignal.values().length) {
            for (SubSignal signal : SubSignal.values()) {
                if (signal.ordinal() == o) {
                    return signal;
                }
            }
        }
        return NONE;

    }

    public static SubSignal toEnum(String topic) {
        if (!StringUtils.isNotBlank(topic)) {
            for (SubSignal signal : SubSignal.values()) {
                if (signal.name().equals(topic)) {
                    return signal;
                }
            }
        }
        return NONE;
    }
}
