package com.tiny.common.response;


import cn.hutool.core.util.ObjectUtil;
import com.tiny.common.util.JsonUtil;

import java.io.Serializable;
import java.util.Optional;

/**
 * 说明：统一 Rest 返回对象
 *
 * @author wangwei
 * @date
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -5359531292427290394L;

    /**
     * 状态码
     */
    private int code;
    /**
     * 是否成功
     */
    private boolean isSuccess;

    private String  message;

    private final long  timestamp= System.currentTimeMillis();

    private T result;

    public Result() {

    }

    public Result(ResponseEnum resultCode) {
        this(resultCode, resultCode.getMessage(),null);
    }

    private Result(ResponseEnum resultCode, String msg) {
        this(resultCode, msg,null);
    }

    private Result(ResponseEnum resultCode, T data) {
        this(resultCode.getCode(), resultCode.getMessage(),data);
    }

    private Result(ResponseEnum resultCode, String msg, T data) {
        this(resultCode.getCode(), msg,data);
    }

    public Result(int code, String message) {
        this.code=code;
        this.message=message;
        this.isSuccess=ResponseEnum.SUCCESS.getCode()==code;
    }

    public Result(int code, String message, T t) {
        this.code=code;
        this.message=message;
        this.result=t;
        this.isSuccess=ResponseEnum.SUCCESS.getCode()==code;
    }

    /**
     * 判断返回是否为成功
     *
     * @param result Result
     * @return 是否成功
     */
    public static boolean isSuccess(com.tiny.common.response.Result<?> result) {
        return Optional.ofNullable(result)
                .map(x -> ObjectUtil.notEqual(ResponseEnum.SUCCESS.getCode(), x.code))
                .orElse(Boolean.FALSE);
    }

    public static <T> com.tiny.common.response.Result<T> success() {
        return new com.tiny.common.response.Result<T>(ResponseEnum.SUCCESS);
    }

    public static  <T> com.tiny.common.response.Result<T> success(T result) {

        return new com.tiny.common.response.Result<>(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMessage(), result);
    }

    public static  <T> com.tiny.common.response.Result<T> success(int code, String message) {

        return new com.tiny.common.response.Result<>(code, message);
    }

    public static  <T> com.tiny.common.response.Result<T> success(int code, String message, T result) {
        return new com.tiny.common.response.Result<>(code, message, result);
    }

    public static <T> com.tiny.common.response.Result<T> failure() {

        return new com.tiny.common.response.Result<>(ResponseEnum.FAILURE);
    }

    public static <T> com.tiny.common.response.Result<T> failure(String errorMessage) {

        return new com.tiny.common.response.Result<>(ResponseEnum.FAILURE.getCode(),errorMessage);
    }

    public static  <T> com.tiny.common.response.Result<T> failure(int errorCode, String errorMessage) {

        return new com.tiny.common.response.Result<>(errorCode, errorMessage);
    }

    public static  <T> com.tiny.common.response.Result<T> failure(int errorCode, String errorMessage, T error) {

        return new com.tiny.common.response.Result<>(errorCode, errorMessage, error);
    }

    public static  <T> com.tiny.common.response.Result<T> failure(IErrorCode errorCode) {

        return new com.tiny.common.response.Result<>(errorCode.getCode(), errorCode.getMessage());
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public void setErrorMessage(String errorMessage) {
    }

    public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
