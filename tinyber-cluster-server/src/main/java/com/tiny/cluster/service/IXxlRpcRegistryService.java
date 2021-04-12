package com.tiny.cluster.service;


import com.tiny.cluster.entity.XxlRpcRegistry;
import com.tiny.cluster.entity.XxlRpcRegistryData;
import com.tiny.common.response.Result;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.Map;

/**
 * @author xuxueli 2016-5-28 15:30:33
 */
public interface IXxlRpcRegistryService {

    // admin
    Map<String,Object> pageList(int start, int length, String biz, String env, String key);
    Result<String> delete(int id);
    Result<String> update(XxlRpcRegistry xxlRpcRegistry);
    Result<String> add(XxlRpcRegistry xxlRpcRegistry);


    // ------------------------ remote registry ------------------------

    /**
     * refresh registry-value, check update and broacase
     */
    Result<String> registry(String accessToken, String biz, String env, List<XxlRpcRegistryData> registryDataList);

    /**
     * remove registry-value, check update and broacase
     */
    Result<String> remove(String accessToken, String biz, String env, List<XxlRpcRegistryData> registryDataList);

    /**
     * discovery registry-data, read file
     */
    Result<Map<String, List<String>>> discovery(String accessToken, String biz, String env, List<String> keys);

    /**
     * monitor update
     */
    DeferredResult<Result<String>> monitor(String accessToken, String biz, String env, List<String> keys);

}
