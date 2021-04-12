package com.tiny.push.push.impl;

import com.tiny.common.util.SpringContextUtil;
import com.tiny.push.push.PushBizService;
import com.tiny.push.push.PushService;
import com.tiny.push.push.bean.PushMessage;
import com.tiny.push.domain.PushRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


/**
 * 推送
 * @author wangwei
 * @date 2020/4/27 9:54:18
 */
@Service
@DependsOn(value = "springContextUtil")
public class PushBizServiceImpl implements PushBizService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PushBizServiceImpl.class);

    private static final String IOS = "iosPushService";

    private static final String ANDROID = "androidPushService";

    private final Map<String, PushService> serviceMap = new HashMap<>();

    public PushBizServiceImpl() {
        SpringContextUtil.getBeansOfType(PushService.class).forEach((key, value) -> {
            if (IOS.equalsIgnoreCase(key)) {
                serviceMap.put("ios", value);
            }
            if (ANDROID.equalsIgnoreCase(key)) {
                serviceMap.put("android", value);
            }
        });
    }
    @Override
    public Boolean pushMessage(PushRequest pushRequest) {
        LOGGER.info("pushMessage push deviceId => [{}], deviceType => [{}]", pushRequest.getDeviceToken(), pushRequest.getDeviceType());
        PushService pushService = serviceMap.get(pushRequest.getDeviceType());
        PushMessage pushMessage = pushService.buildMessage(pushRequest);
        return serviceMap.get(pushRequest.getDeviceType()).unicast(pushRequest.getDeviceToken(),pushRequest.getPlatform(), pushMessage);
    }
}
