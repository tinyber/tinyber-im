package com.tiny.common.chat;


/**
 * @author way
 */
public class ChatBody extends BaseRequest{

    /**
     * 消息ID
     */
    private String msId;

    /**
     * 类型1文字 2emoji 3音频 4文件 5图片 6视频
     */
    private String type;

    /**
     * 消息标题
     */
    private String title;

    /**
     * content 内容格式  text,json ,xml
     */
    private String format;

    /**
     * 内容 消息类容，于type 组合为任何类型消息，content 根据 format 可表示为 text,json ,xml数据格式
     */
    private String content;

    /**
     * 类型 0 单聊消息  1 群消息
     */
    private Integer chatType;

    /**
     * 发送Id
     */
    private String sender;

    /**
     * 接受ID
     */
    private String receiver;

    /**
     * 房间群组ID
     */
    private String groupId;

    /**
     * 创建时间
     */
    private long timestamp;

    /**
     * 附加内容 内容
     */
    private String extras;


    public String getMsId() {
        return msId;
    }

    public void setMsId(String msId) {
        this.msId = msId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getChatType() {
        return chatType;
    }

    public void setChatType(Integer chatType) {
        this.chatType = chatType;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }
}
