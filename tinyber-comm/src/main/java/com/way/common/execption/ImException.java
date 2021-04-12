package com.tiny.common.execption;

import com.tiny.common.constants.enums.StatusEnum;

public class ImException extends GenericException {
    
    public ImException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ImException(Exception e, String errorCode, String errorMessage) {
        super(e, errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ImException(String message) {
        super(message);
        this.errorMessage = message;
    }

    public ImException(StatusEnum statusEnum) {
        super(statusEnum.getMessage());
        this.errorMessage = statusEnum.message();
        this.errorCode = statusEnum.getCode();
    }

    public ImException(StatusEnum statusEnum, String message) {
        super(message);
        this.errorMessage = message;
        this.errorCode = statusEnum.getCode();
    }

    public ImException(Exception oriEx) {
        super(oriEx);
    }

    public ImException(Throwable oriEx) {
        super(oriEx);
    }

    public ImException(String message, Exception oriEx) {
        super(message, oriEx);
        this.errorMessage = message;
    }

    public ImException(String message, Throwable oriEx) {
        super(message, oriEx);
        this.errorMessage = message;
    }


    public static boolean isResetByPeer(String msg) {
        if ("Connection reset by peer".equals(msg)) {
            return true;
        }
        return false;
    }

}
