package com.tiny.store.domain;

import java.util.List;

/**
 * @author wangwei
 */
public class GroupRecord {

    /**
     * 群组ID
     */
    private String groupId;
    /**
     * 群组名称
     */
    private String name;
    /**
     * 群组头像
     */
    private String avatar;
    /**
     * 在线人数
     */
    private Integer online;
    /**
     * 组用户
     */
    private List<String> uid;

    private List<GroupUserRecord> groupsUser;

    /**
     * 额外
     */
    private String extras;

    /**
     * 状态
     */
    private Integer state;

    public GroupRecord() {
    }

    private GroupRecord(String groupId, String name, String avatar, Integer online, List<String> uid, String extras) {
        this.groupId = groupId;
        this.name = name;
        this.avatar = avatar;
        this.online = online;
        this.uid = uid;
        this.extras = extras;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public List<String> getUid() {
        return uid;
    }

    public void setUid(List<String> uid) {
        this.uid = uid;
    }

    public List<GroupUserRecord> getGroupsUser() {
        return groupsUser;
    }

    public void setGroupsUser(List<GroupUserRecord> groupsUser) {
        this.groupsUser = groupsUser;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
