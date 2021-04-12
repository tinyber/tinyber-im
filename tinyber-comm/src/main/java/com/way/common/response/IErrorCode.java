package com.tiny.common.response;

/**
 * 说明：
 *
 * @author
 * @version
 */
public interface IErrorCode {

    /**
     * 获取返回Code
     *
     * @return code
     */
    int getCode();

    /**
     * 获取返回Code
     *
     * @return message
     */
    String getMessage();

    /**
     * 获取nameSpace
     *
     * @return nameSpace
     */
    String getNameSpace();

    /**
     * 获取错误码

     * @return 错误码
     */
    String getErrorCode();

    /**
     * 获取错误信息

     * @return 错误信息
     */
    String getErrorMessage();
}
