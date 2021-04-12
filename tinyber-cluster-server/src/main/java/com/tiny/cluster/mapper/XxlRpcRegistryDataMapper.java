package com.tiny.cluster.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tiny.cluster.entity.XxlRpcRegistry;
import com.tiny.cluster.entity.XxlRpcRegistryData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XxlRpcRegistryDataMapper extends BaseMapper<XxlRpcRegistry> {

    public int refresh(@Param("xxlRpcRegistryData") XxlRpcRegistryData xxlRpcRegistryData);

    public int add(@Param("xxlRpcRegistryData") XxlRpcRegistryData xxlRpcRegistryData);


    public List<XxlRpcRegistryData> findData(@Param("biz") String biz,
                                             @Param("env") String env,
                                             @Param("key") String key);

    public int cleanData(@Param("timeout") int timeout);

    public int deleteData(@Param("biz") String biz,
                          @Param("env") String env,
                          @Param("key") String key);

    public int deleteDataValue(@Param("biz") String biz,
                               @Param("env") String env,
                               @Param("key") String key,
                               @Param("value") String value);

    public int count();
}
