package com.tiny.common.response;


/**
 * 系统基本返回码
 *
 * @author wangwei
 */

public enum ResponseEnum implements IErrorCode {

    /**
     * 操作成功
     */
    SUCCESS(200, "success"),

    /**
     * 业务异常
     */
    FAILURE(500, "failed"),

    ;

    private final int code;

    private final String message;

    ResponseEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    private static final String NS = "Sys";

    @Override
    public String getNameSpace() {
        return NS;
    }

    @Override
    public String getErrorCode() {
        return NS + "." + this.code;
    }

    @Override
    public String getErrorMessage() {
        return this.message;
    }


    public static ResponseEnum getResultEnumCode(Integer code) {
        for (ResponseEnum type : ResponseEnum.values()) {
            if (type.getCode()==code.intValue()) {
                return type;
            }
        }
        return FAILURE;
    }

    public static ResponseEnum getResultEnumMsg(String message) {
        for (ResponseEnum type : ResponseEnum.values()) {
            if (type.getMessage().equals(message)) {
                return type;
            }
        }
        return FAILURE;
    }

}
