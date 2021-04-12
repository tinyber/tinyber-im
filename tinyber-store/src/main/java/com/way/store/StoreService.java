package com.tiny.store;


import com.tiny.store.domain.GroupRecord;
import com.tiny.store.domain.MessageRecord;

import java.util.List;

/**
 * @author way
 */
public interface StoreService {

    /**
     * 分页
     * @param clinicId 聊天ID
     * @param page     页码
     * @param limit    大小
     * @param msId     消息Id
     */
    PageInfo<MessageRecord> pageList(String clinicId, Integer page, Integer limit, String msId);

    MessageRecord save(MessageRecord messageRecord);

    /**
     * 保存聊天消息
     *
     * @param msgId
     * @return
     */
    boolean modifyStatus(String msgId);

    List<GroupRecord> queryUserGroup(String userId);

    /**
     * 保存群聊信息
     *
     * @param groupRecord
     */
    void saveGroup(GroupRecord groupRecord);

    /**
     * 初始群聊信息
     *
     * @param groupRecord
     */
    void initGroupUser(GroupRecord groupRecord);

    /**
     * 更新群聊信息
     *
     * @param clinicId
     */
    void updateGroupState(String clinicId);

    /**
     * 更新群聊状态
     * @param clinicId
     * @param userId
     * @return
     */
    Boolean updateGroupUserState(String clinicId, String userId);

}
