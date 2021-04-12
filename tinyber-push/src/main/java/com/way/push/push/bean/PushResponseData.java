package com.tiny.push.push.bean;

/**
 * 推送
 * @author wangwei
 * @date 2020/4/27 9:54:18
 */
public class PushResponseData {

    private String msg_id;

    private String task_id;

    private String error_code;

    private String error_msg;

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }
}
