package com.tiny.common.packet;

import org.apache.commons.lang3.StringUtils;

/***
 * 基本信令定义
 **/
public enum Signal {
    /**
     *
     */
    NONE(1),
    //IM链接信令,
    CONNECT(2),
    //聊天信令
    CONTACT(3),
    //订阅信令
    USER(4),
    //鉴权信令
    AUTH(5),
    //心跳指令
    PING(6),
    //推送指令
    PUSH(7),
    //读取消息
    RECEIVER_ACK(8),

    SUB(9),
    /**
     * 拉取在线状态
     */
    ONLINE(10),
    /**
     * 断开连接
     */
    UN_CONNECT(12),
    PUBLISH(13),
    PUB_ACK(14);


    Signal(Integer value) {
        this.value = (byte) value.intValue();
    }

    public static Signal toEnum(int ordinal) {
        byte o = (byte) ordinal;
        if (o > NONE.ordinal() &&
                o < Signal.values().length) {
            for (Signal signal : Signal.values()) {
                if (signal.ordinal() == o) {
                    return signal;
                }
            }
        }
        return NONE;
    }

    public static Signal toEnum(String topic) {
        if (StringUtils.isNoneEmpty(topic)) {
            for (Signal signal : Signal.values()) {
                if (signal.name().equals(topic)) {
                    return signal;
                }
            }
        }
        return NONE;
    }

    private final Byte value;

    public static Signal forNumber(int cmd_number) {
        return Signal.AUTH;
    }


    public Byte getValue() {
        return value;
    }
}
