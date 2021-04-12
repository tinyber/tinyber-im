package com.gz.store.mongo.constants.enums;

public enum GroupStatusType {
    /**
     * <pre>
     * 在线
     * </pre>
     *
     * <code>ONLINE = 0;</code>
     */
    OFF(0, "online", "禁用"),
    /**
     * <pre>
     * 离线
     * </pre>
     *
     * <code>OFFLINE = 1;</code>
     */
    ON(1, "offline", "启用"),
    ;


    public final int getNumber() {
        return value;
    }

    public static GroupStatusType valueOf(int value) {
        return forNumber(value);
    }

    public static GroupStatusType forNumber(int value) {
        switch (value) {
            case 0:
                return OFF;
            case 1:
                return ON;
            default:
                return null;
        }
    }

    private final int value;

    private final String status;

    private final String desc;

    GroupStatusType(int value, String status, String desc) {
        this.value = value;
        this.status = status;
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }

}
