package com.tiny.push.push.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.tiny.common.util.JsonUtil;
import com.tiny.push.push.PushService;
import com.tiny.push.push.bean.*;
import com.tiny.push.push.properties.UmengAndroidDoctorProperties;
import com.tiny.push.push.properties.UmengAndroidPatientProperties;
import com.tiny.push.domain.PushRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


/**
 * @ClassName AndroidPushServiceImpl
 * @Description 安卓推送
 * @Author
 * @Date 2020/3/31 18:16
 * @Version V1.0
 **/
@Service("androidPushService")
public class AndroidPushServiceImpl implements PushService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AndroidPushServiceImpl.class);

    private final UmengAndroidDoctorProperties umengDoctorProperties;

    private final UmengAndroidPatientProperties umengPatientProperties;

    private static final String SUCCESS = "SUCCESS";

    private final boolean productionMode;

    public AndroidPushServiceImpl(UmengAndroidDoctorProperties umengDoctorProperties, UmengAndroidPatientProperties umengPatientProperties) {
        this.umengDoctorProperties = umengDoctorProperties;
        this.umengPatientProperties = umengPatientProperties;
        this.productionMode = true;
    }

    @Override
    public boolean unicast(String deviceId,Integer platform, PushMessage pushMessage) {
        String url = String.format("%s%s%s", umengPatientProperties.getUrl(), "?sign=", sign(pushMessage,platform));
        LOGGER.info("url => [{}] body => [{}]", url, JsonUtil.toJson(pushMessage));
        HttpResponse execute = HttpRequest.post(url).body(JsonUtil.toJson(pushMessage)).execute();
        String response=execute.body();
        LOGGER.info("消息PUSH返回： [{}]", response);
        PushResponse pushResponse = JsonUtil.parse(response, PushResponse.class);
        if (!SUCCESS.equalsIgnoreCase(pushResponse.getRet())) {
            LOGGER.error("友盟Android推送失败, response => [{}]", response);
            return false;
        }
        return true;
    }

    @Override
    public PushMessage buildMessage(PushRequest pushRequest) {
        AndroidPushBody pushBody = new AndroidPushBody();
        pushBody.setTitle(pushRequest.getTitle());
        pushBody.setTicker(pushRequest.getSubTitle());
        pushBody.setText(pushRequest.getMessage());
        pushBody.setSound(pushRequest.getSound());
        CustomBody customBody = new CustomBody();
        customBody.setAction(pushRequest.getCustom());
        pushBody.setCustom(customBody);

        AndroidPayload pushPayload = new AndroidPayload(umengPatientProperties.getDisplayType(), pushBody);

        return new AndroidPushMessage("","",umengPatientProperties.getAppKey(), System.currentTimeMillis(), umengPatientProperties.getPushType(), pushRequest.getDeviceToken(), productionMode, pushPayload);
    }

    private String sign(PushMessage pushMessage,Integer platform) {
        String httpMethod = HttpMethod.POST.name();
        String postBody = JsonUtil.toJson(pushMessage);
        String sign = String.format("%s%s%s%s", httpMethod, umengPatientProperties.getUrl(), postBody, umengPatientProperties.getMasterSecret());
        LOGGER.info("android sign => [{}]", sign);
        return DigestUtils.md5DigestAsHex(sign.getBytes());
    }
}
