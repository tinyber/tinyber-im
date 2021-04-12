package com.gz.store.mongo.service;


import com.tiny.store.StoreService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author way
 */
@Service
public class MongoStoreImpl implements StoreService {

    private final MongoStorage mongoStorage;

    public MongoStoreImpl(MongoStorage mongoStorage) {
        this.mongoStorage = mongoStorage;
    }

    @Override
    public PageInfo<MessageRecord> pageList(String clinicId, Integer page, Integer limit, String msId) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        if (!StringUtils.isEmpty(clinicId)) {
            criteria.and("clinicId").is(clinicId);
        }
        query.addCriteria(criteria);
        long total = this.mongoStorage.count(query, MessageRecord.class, GroupCollectEnum.CHAT.getFeatureName());
        int pages = (int) Math.ceil((double) total / (double) limit);

        PageInfo<MessageRecord> pageInfoResult = new PageInfo<>();
        pageInfoResult.setAllRow((int) total);
        pageInfoResult.setTotalPage(pages);
        pageInfoResult.setPageSize(limit);
        pageInfoResult.setCurrentPage(page);
        pageInfoResult.setList(Collections.emptyList());
        if (page <= 0 || page > pages) {
            return pageInfoResult;
        }
        int skip = limit * (page - 1);
        query.with(Sort.by(Sort.Order.desc("createTime"))).skip(skip)
                .limit(limit);
        List<MessageRecord> list = mongoStorage.chatMessageList(query, GroupCollectEnum.CHAT.getFeatureName());
        List<MessageRecord> imList = list.stream().sorted(Comparator.comparing(MessageRecord::getSendTime)).collect(Collectors.toList());
        pageInfoResult.setList(imList);
        return pageInfoResult;
    }

    @Override
    public MessageRecord save(MessageRecord messageRecord) {
        return mongoStorage.saveChatMessage(messageRecord, GroupCollectEnum.CHAT.getFeatureName());
    }

    @Override
    public boolean modifyStatus(String msgId) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        if (!StringUtils.isEmpty(msgId)) {
            criteria.and("msgId").is(msgId);
        }
        Update update = new Update();
        update.set("isReceiver", 1);
        mongoStorage.updateChatMessage(query, update, MessageRecord.class);
        return true;

    }

    @Override
    public List<GroupRecord> queryUserGroup(String userId) {
        LookupOperation lookupOperation = LookupOperation.newLookup().
                //关联从表名
                        from(GroupCollectEnum.GROUP_USER.getFeatureName()).
                //主表关联字段
                        localField("groupId").
                //从表关联的字段
                        foreignField("groupId").
                //查询结果名
                        as("groupsUser");
        //带条件查询可以选择添加下面的条件
        Criteria criteria = new Criteria();
        criteria.and("state").is(GroupStatusType.ON.getNumber());
        AggregationOperation match = Aggregation.match(criteria);
        Aggregation aggregation = Aggregation.newAggregation(lookupOperation, match);
        return mongoStorage.aggregate(aggregation, GroupCollectEnum.GROUP.getFeatureName(), GroupRecord.class).getMappedResults();

    }

    @Override
    public void saveGroup(GroupRecord groupRecord) {
        mongoStorage.saveGroup(groupRecord, GroupCollectEnum.GROUP.getFeatureName());
    }

    @Override
    public void initGroupUser(GroupRecord groupRecord) {
        mongoStorage.saveGroupUser(groupRecord, GroupCollectEnum.GROUP_USER.getFeatureName());
    }

    @Override
    public void updateGroupState(String clinicId) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        if (!StringUtils.isEmpty(clinicId)) {
            criteria.and("clinicId").is(clinicId);
        }
        Update update = new Update();
        update.set("state", GroupStatusType.OFF.getNumber());
        mongoStorage.updateGroupState(query, update, GroupRecord.class, GroupCollectEnum.GROUP.getFeatureName());

    }

    @Override
    public Boolean updateGroupUserState(String clinicId, String userId) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        if (!StringUtils.isEmpty(clinicId)) {
            criteria.and("clinicId").is(clinicId);
        }
        if (!StringUtils.isEmpty(userId)) {
            criteria.and("userId").is(userId);
        }
        Update update = new Update();
        update.set("status", GroupStatusType.OFF.getNumber());
        return  mongoStorage.updateGroupUserState(query, update, GroupUserRecord.class, GroupCollectEnum.GROUP.getFeatureName());

    }
}
