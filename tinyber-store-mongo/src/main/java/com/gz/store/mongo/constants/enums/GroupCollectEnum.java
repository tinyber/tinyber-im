package com.gz.store.mongo.constants.enums;

/**
 * @author by wangwei
 * @ClassName GroupCollectEnum
 * @Description TODO
 * @Date 2020/9/27 9:48
 */
public enum GroupCollectEnum {

    /**
     *
     */
    CHAT(1, "chat", "聊天信息"),
    GROUP_USER(2, "group_user ", "群聊成员"),
    GROUP(3, "group", "群聊信息"),
    ;
    /**
     * 类型
     */
    private final int featureType;
    /**
     * 名称(数据库feature存储名)
     */
    private final String featureName;
    /**
     * 描叙
     */
    private final String description;


    GroupCollectEnum(int featureType, String featureName, String description) {
        this.featureType = featureType;
        this.featureName = featureName;
        this.description = description;
    }

    public static GroupCollectEnum getValueByType(int featureType) {
        for (GroupCollectEnum enums : values()) {
            if (enums.getFeatureType() == featureType) {
                return enums;
            }
        }
        return null;
    }

    public static GroupCollectEnum getValueByName(String featureName) {
        for (GroupCollectEnum enums : values()) {
            //不区分大小写返回
            if (enums.getFeatureName().equalsIgnoreCase(featureName)) {
                return enums;
            }
        }
        return null;
    }

    /**
     * @return the featureType
     */
    public int getFeatureType() {
        return featureType;
    }

    /**
     * @return the featureName
     */
    public String getFeatureName() {
        return featureName;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
}
