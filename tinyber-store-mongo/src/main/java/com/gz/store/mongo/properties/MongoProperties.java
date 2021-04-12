package com.gz.store.mongo.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author wangwei
 * @Date 2020/5/9 14:10
 */
@Configuration
@ConfigurationProperties(prefix = "spring.data.mongodb")
public class MongoProperties {

    private String database;

    private String collectionName;

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }
}
