package com.tiny.cluster.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tiny.cluster.entity.XxlRpcRegistry;
import com.tiny.cluster.entity.XxlRpcRegistryMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XxlRpcRegistryMessageMapper extends BaseMapper<XxlRpcRegistry> {

    public int add(@Param("xxlRpcRegistryMessage") XxlRpcRegistryMessage xxlRpcRegistryMessage);

    public List<XxlRpcRegistryMessage> findMessage(@Param("excludeIds") List<Integer> excludeIds);

    public int cleanMessage(@Param("messageTimeout") int messageTimeout);
}
