package com.tiny.store.domain;

import java.util.Date;

/**
 * @author way
 */
public class MessageRecord {
    
    private String messageId;

    /**
     * 诊疗ID
     */
    private String clinicId;
    /**
     * 内容
     */
    private String content;

    /**
     * 类型1文字 2emoji 3音频 4文件 5图片 6视频
     */
    private String type;

    /**
     * 类型 0 单聊消息  1 群消息
     */
    private Integer chatType;

    /**
     * 是否已读
     */
    private String readStatus;

    /**
     * 发送Id
     */
    private String senderId;

    /**
     * 接受ID
     */
    private String receiverId;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * 读取时间
     */
    private Date readTime;

    /**
     * 房间群组ID
     */
    private String groupId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否已接收到
     */
    private Integer isReceiver;


    private String extras;


    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public String getClinicId() {
        return clinicId;
    }

    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }

    private static final long serialVersionUID = 1L;

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取类型1文字 2emoji 3音频 4文件 5图片 6视频
     *
     * @return type - 类型1文字 2emoji 3音频 4文件 5图片 6视频
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型1文字 2emoji 3音频 4文件 5图片 6视频
     *
     * @param type 类型1文字 2emoji 3音频 4文件 5图片 6视频
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取类型 0 单聊消息  1 群消息
     *
     * @return chat_type - 类型 0 单聊消息  1 群消息
     */
    public Integer getChatType() {
        return chatType;
    }

    /**
     * 设置类型 0 单聊消息  1 群消息
     *
     * @param chatType 类型 0 单聊消息  1 群消息
     */
    public void setChatType(Integer chatType) {
        this.chatType = chatType;
    }

    /**
     * 获取是否已读
     *
     * @return read_status - 是否已读
     */
    public String getReadStatus() {
        return readStatus;
    }

    /**
     * 设置是否已读
     *
     * @param readStatus 是否已读
     */
    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus == null ? null : readStatus.trim();
    }

    /**
     * 获取发送Id
     *
     * @return sender_id - 发送Id
     */
    public String getSenderId() {
        return senderId;
    }

    /**
     * 设置发送Id
     *
     * @param senderId 发送Id
     */
    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    /**
     * 获取接受ID
     *
     * @return receiver_id - 接受ID
     */
    public String getReceiverId() {
        return receiverId;
    }

    /**
     * 设置接受ID
     *
     * @param receiverId 接受ID
     */
    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    /**
     * 获取发送时间
     *
     * @return send_time - 发送时间
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * 设置发送时间
     *
     * @param sendTime 发送时间
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * 获取读取时间
     *
     * @return read_time - 读取时间
     */
    public Date getReadTime() {
        return readTime;
    }

    /**
     * 设置读取时间
     *
     * @param readTime 读取时间
     */
    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    /**
     * 获取房间群组ID
     *
     * @return group_id - 房间群组ID
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * 设置房间群组ID
     *
     * @param groupId 房间群组ID
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Integer getIsReceiver() {
        return isReceiver;
    }

    public void setIsReceiver(Integer isReceiver) {
        this.isReceiver = isReceiver;
    }
}
