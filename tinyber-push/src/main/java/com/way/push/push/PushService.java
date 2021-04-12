package com.tiny.push.push;


import com.tiny.push.push.bean.PushMessage;
import com.tiny.push.domain.PushRequest;

/**
 * @author Administrator
 */
public interface PushService {

    /**
     * 单推
     * @param deviceId
     * @param platform
     * @param pushMessage
     * @return
     */
    boolean unicast(String deviceId,Integer platform, PushMessage pushMessage);

    PushMessage buildMessage(PushRequest pushRequest);
}
