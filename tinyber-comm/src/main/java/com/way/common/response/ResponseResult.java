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
public class ResponseResult<T> implements Serializable {

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

    public ResponseResult() {

    }

    public ResponseResult(ResponseEnum resultCode) {
        this(resultCode, resultCode.getMessage(),null);
    }

    private ResponseResult(ResponseEnum resultCode, String msg) {
        this(resultCode, msg,null);
    }

    private ResponseResult(ResponseEnum resultCode, T data) {
        this(resultCode.getCode(), resultCode.getMessage(),data);
    }

    private ResponseResult(ResponseEnum resultCode,String msg, T data) {
        this(resultCode.getCode(), msg,data);
    }

    public ResponseResult(int code, String message) {
        this.code=code;
        this.message=message;
        this.isSuccess=ResponseEnum.SUCCESS.getCode()==code;
    }

    public ResponseResult(int code, String message,T t) {
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
    public static boolean isSuccess(ResponseResult<?> result) {
        return Optional.ofNullable(result)
                .map(x -> ObjectUtil.notEqual(ResponseEnum.SUCCESS.getCode(), x.code))
                .orElse(Boolean.FALSE);
    }

    public static <T>  ResponseResult<T> success() {
        return new ResponseResult<T>(ResponseEnum.SUCCESS);
    }

    public static  <T> ResponseResult<T>  success(T result) {

        return new ResponseResult<>(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMessage(), result);
    }

    public static  <T> ResponseResult<T>  success(int code,String message) {

        return new ResponseResult<>(code, message);
    }

    public static  <T> ResponseResult<T>  success(int code,String message,T result) {
        return new ResponseResult<>(code, message, result);
    }

    public static <T>  ResponseResult<T> failure() {

        return new ResponseResult<>(ResponseEnum.FAILURE);
    }

    public static <T>  ResponseResult<T> failure(String errorMessage) {

        return new ResponseResult<>(ResponseEnum.FAILURE.getCode(),errorMessage);
    }

    public static  <T>  ResponseResult<T> failure(int errorCode, String errorMessage) {

        return new ResponseResult<>(errorCode, errorMessage);
    }

    public static  <T> ResponseResult<T> failure(int errorCode, String errorMessage,T error) {

        return new ResponseResult<>(errorCode, errorMessage, error);
    }

    public static  <T>  ResponseResult<T> failure(IErrorCode errorCode) {

        return new ResponseResult<>(errorCode.getCode(), errorCode.getMessage());
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
