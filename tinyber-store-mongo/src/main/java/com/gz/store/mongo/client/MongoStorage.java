package com.gz.store.mongo.client;

import com.gz.store.domain.GroupRecord;
import com.gz.store.domain.GroupUserRecord;
import com.gz.store.domain.MessageRecord;
import com.gz.store.mongo.constants.enums.GroupStatusType;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author wangwei
 * @Date 2020/5/11 20:09
 */
@Component
public class MongoStorage {

    private final MongoTemplate mongoTemplate;

    public MongoStorage(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public MessageRecord saveChatMessage(MessageRecord messageRecord, String collectionName) {
        return mongoTemplate.save(messageRecord, collectionName);
    }

    public List<MessageRecord> chatMessageList(Query query, String collectionName) {
        return mongoTemplate.find(query, MessageRecord.class, collectionName);
    }

    public Long count(Query query, Class<MessageRecord> chatVoClass, String collectionName) {
        return mongoTemplate.count(query, chatVoClass, collectionName);
    }

    public void updateChatMessage(Query query, Update update, Class<MessageRecord> chatVoClass) {
        mongoTemplate.upsert(query, update, chatVoClass);
    }

    public List<GroupRecord> queryUserGroupList(Query query, String collectionName) {

        return mongoTemplate.find(query, GroupRecord.class, collectionName);
    }

    public void saveGroup(GroupRecord groupRecord, String collectionName) {

        mongoTemplate.save(groupRecord, collectionName);
    }

    public void updateGroupState(Query query, Update update, Class<GroupRecord> groupClass, String collectionName) {
        mongoTemplate.upsert(query, update, groupClass, collectionName);
    }

    public void saveGroupUser(GroupRecord groupRecord, String collectionName) {
        List<GroupUserRecord> list = groupRecord.getUid().stream().map(uid -> {
            GroupUserRecord groupUserRecord = new GroupUserRecord();
            groupUserRecord.setGroupId(groupRecord.getGroupId());
            groupUserRecord.setStatus(GroupStatusType.ON.getNumber());
            groupUserRecord.setUserId(uid);
            return groupUserRecord;
        }).collect(Collectors.toList());
        mongoTemplate.insert(list, collectionName);
    }

    public boolean updateGroupUserState(Query query, Update update, Class<GroupUserRecord> groupUserClass, String featureName) {
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, groupUserClass, featureName);
        return updateResult.getModifiedCount()>0L;
    }

    public AggregationResults<GroupRecord> aggregate(Aggregation aggregation, String featureName, Class<GroupRecord> mapClass) {

        return mongoTemplate.aggregate(aggregation, featureName, mapClass);
    }
}
