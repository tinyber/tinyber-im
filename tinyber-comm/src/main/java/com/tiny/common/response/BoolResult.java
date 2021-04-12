package com.tiny.common.response;

/**
 * 登录
 * @author wangwei
 */
public class BoolResult {

    /**
     * 业务是否成功 true-成功或存在 false-失败或不存在", example = "true", dataType = "boolean"
     */
    private boolean bizSuccess;

    public BoolResult() {
    }

    public BoolResult(boolean bizSuccess) {
        this.bizSuccess = bizSuccess;
    }

    public boolean isBizSuccess() {
        return bizSuccess;
    }

    public void setBizSuccess(boolean bizSuccess) {
        this.bizSuccess = bizSuccess;
    }
}
