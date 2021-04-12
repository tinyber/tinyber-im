package com.tiny.common.chat;

/**
 * @author by wangwei
 * @ClassName OffLineMessage
 * @Description TODO
 * @Date 2020/12/24 15:55
 */
public class OffLineMessage {

    /**
     * 消息ID
     */
    private String mId;

    /**
     * 发送者头像
     */
    private String avatar;

    /**
     * 发送者姓名
     */
    private String name;

    /**
     * 消息类型
     */
    private Integer type;

    /**
     *是否已读
     */
    private Integer readFlag;

    /**
     * 已读时间
     */
    private Integer readTime;

    /**
     * 时间
     */
    private String createdTime;

    /**
     * 发送者姓名
     */
    private String receiverName;

    private String key;

    private String sendBySys;
    /**
     * 内容
     */
    private String content;


}
