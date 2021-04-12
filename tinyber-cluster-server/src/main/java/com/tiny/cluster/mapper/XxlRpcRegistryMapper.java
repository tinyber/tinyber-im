package com.tiny.cluster.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tiny.cluster.entity.XxlRpcRegistry;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XxlRpcRegistryMapper extends BaseMapper<XxlRpcRegistry> {


    public List<XxlRpcRegistry> pageList(@Param("offset") int offset,
                                         @Param("pagesize") int pagesize,
                                         @Param("biz") String biz,
                                         @Param("env") String env,
                                         @Param("key") String key);
    public int pageListCount(@Param("offset") int offset,
                             @Param("pagesize") int pagesize,
                             @Param("biz") String biz,
                             @Param("env") String env,
                             @Param("key") String key);

    public XxlRpcRegistry load(@Param("biz") String biz,
                               @Param("env") String env,
                               @Param("key") String key);

    public XxlRpcRegistry loadById(@Param("id") int id);

    public int add(@Param("xxlRpcRegistry") XxlRpcRegistry xxlRpcRegistry);

    public int update(@Param("xxlRpcRegistry") XxlRpcRegistry xxlRpcRegistry);

    public int delete(@Param("id") int id);
}
