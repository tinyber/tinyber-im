package com.tiny.common.chat;

public enum Platform {

    PLATFORM_TYPE_UNSET(0),
    PLATFORM_TYPE_IOS(1),
    PLATFORM_TYPE_ANDROID(2),
    PLATFORM_TYPE_WINDOWS(3),
    PLATFORM_TYPE_OSX(4),
    PLATFORM_TYPE_WEB(5),
    PLATFORM_TYPE_WX(6),
    PLATFORM_TYPE_LINUX(7);

    private int value;

    Platform(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public static Platform platform(int platform) {
        if (platform >= 0 && platform < 7) {
            return Platform.values()[platform];
        }
        return Platform.PLATFORM_TYPE_UNSET;
    }

    public String getPlatFormName() {
        String platFormName = "PC";
        switch (this) {
            case PLATFORM_TYPE_WINDOWS:
                platFormName = "Windows";
                break;
            case PLATFORM_TYPE_OSX:
                platFormName = "Mac";
                break;
            case PLATFORM_TYPE_LINUX:
                platFormName = "Linux";
                break;
            case PLATFORM_TYPE_WEB:
                platFormName = "Web";
                break;
            case PLATFORM_TYPE_WX:
                platFormName = "小程序";
                break;
            default:
                break;
        }
        return platFormName;
    }
}