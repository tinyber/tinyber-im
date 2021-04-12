package com.tiny.push.push;


import com.tiny.push.domain.PushRequest;

/**
 * @ClassName IosPushServiceImpl
 * @Description 安卓推送
 * @Author
 * @Date 2020/3/31 18:16
 * @Version V1.0
 **/
public interface PushBizService {

    /**
     * 根据设备Token推送单个消息
     * @param pushRequest
     * @return
     */
    Boolean pushMessage(PushRequest pushRequest);
}
