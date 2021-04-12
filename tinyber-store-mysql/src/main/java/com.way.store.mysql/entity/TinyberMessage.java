package com.tiny.store.mysql.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 消息记录表
 * </p>
 *
 * @author yangjun
 * @since 2021-03-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TinyberMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 聊天
     */
    private String chatId;

    /**
     * 消息标题
     */
    private String title;

    /**
     * 消息发送者账号
     */
    private String sender;

    /**
     * 消息接收者账号
     */
    private String receiver;

    /**
     * 类型1文字 2emoji 3音频 4文件 5图片 6视频
     */
    private Integer type;

    /**
     * 群组ID
     */
    private Long groupId;

    /**
     * 类型 0 单聊消息  1 群消息 2 系统
     */
    private Integer chatType;

    /**
     * 内容 消息类容
     */
    private String content;

    /**
     * 内容 消息类容，于type 组合为任何类型消息，content 根据 format 可表示为 text,json ,xml数据格式
     */
    private String format;

    /**
     * 附加内容 内容
     */
    private String extras;

    /**
     * 消息状态0: 未接收 1：已接收 2:已读
     */
    private Integer state;

    /**
     * 消息产生时间
     */
    private Date timestamp;


}
