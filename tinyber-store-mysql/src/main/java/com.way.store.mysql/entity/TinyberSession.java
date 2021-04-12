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
 * 在线设备表
 * </p>
 *
 * @author yangjun
 * @since 2021-03-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TinyberSession implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 账号ID
     */
    private Integer uId;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 长连接在本台服务器上的ID
     */
    private String channelId;

    /**
     * 设备唯一号
     */
    private String deviceId;

    /**
     * session绑定的服务器IP
     */
    private String hostIp;

    /**
     * 终端设备类型 android ios browser mini
     */
    private String channel;

    /**
     * 终端设备型号 小米
     */
    private String deviceType;

    /**
     * 终端应用版本 
     */
    private String clientVersion;

    /**
     * 终端系统版本
     */
    private String systemVersion;

    /**
     * 登录时间
     */
    private Date bindTime;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 位置
     */
    private String location;

    /**
     * 0在线，1离线
     */
    private String state;


}
