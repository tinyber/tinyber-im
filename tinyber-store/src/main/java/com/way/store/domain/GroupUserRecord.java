package com.tiny.store.domain;

/**
 * @author wangwei
 */
public class GroupUserRecord {

    private static final long serialVersionUID = -3817755433171220952L;
    /**
     * 群组ID
     */
    private String groupId;

    private String userId;

    private Integer status;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
