package com.tiny.push.push.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.tiny.common.util.JsonUtil;
import com.tiny.push.push.PushService;
import com.tiny.push.push.bean.*;
import com.tiny.push.push.properties.UmengIosDoctorProperties;
import com.tiny.push.push.properties.UmengIosPatientProperties;
import com.tiny.push.domain.PushRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @ClassName IosPushServiceImpl
 * @Description IOS推送
 * @Author
 * @Date 2020/3/31 18:16
 * @Version V1.0
 **/
@Service("iosPushService")
public class IosPushServiceImpl implements PushService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IosPushServiceImpl.class);

    private static final String SUCCESS = "SUCCESS";

    private final UmengIosDoctorProperties umengIosDoctorProperties;

    private final UmengIosPatientProperties umengIosPatientProperties;

    private final boolean productionMode;

    public IosPushServiceImpl(UmengIosDoctorProperties umengIosDoctorProperties, UmengIosPatientProperties umengIosPatientProperties) {
        this.umengIosDoctorProperties = umengIosDoctorProperties;
        this.umengIosPatientProperties = umengIosPatientProperties;
        this.productionMode = true;
    }

    @Override
    public boolean unicast(String deviceId,Integer platform, PushMessage pushMessage) {
        String url = String.format("%s%s%s", umengIosDoctorProperties.getUrl(), "?sign=", sign(pushMessage,platform));
        LOGGER.info("url => [{}] body => [{}]", url, JsonUtil.toJson(pushMessage));
        HttpResponse execute = HttpRequest.post(url).body(JsonUtil.toJson(pushMessage)).execute();
        String response=execute.body();
        LOGGER.info("消息PUSH返回： [{}]", response);
        PushResponse pushResponse = JsonUtil.parse(response, PushResponse.class);
        if (!SUCCESS.equalsIgnoreCase(pushResponse.getRet())) {
            LOGGER.error("友盟IOS推送失败, response => [{}]", response);
            return false;
        }
        return true;
    }

    @Override
    public PushMessage buildMessage(PushRequest pushRequest) {
        IosPushBody pushBody = new IosPushBody();
        AlertBody alertBody = new AlertBody();
        alertBody.setTitle(pushRequest.getTitle());
        //alertBody.setSubtitle(pushRequest.getSubTitle());
        alertBody.setBody(pushRequest.getMessage());
        pushBody.setSound(pushRequest.getSound());
        CustomBody customBody = new CustomBody();
        customBody.setAction(pushRequest.getCustom());
        //alertBody.setBody(customBody);
        pushBody.setAlert(alertBody);
        IosPayload pushPayload = new IosPayload();
        pushPayload.setAps(pushBody);
        return new IosPushMessage(umengIosPatientProperties.getAppKey(), System.currentTimeMillis(), umengIosPatientProperties.getPushType(), pushRequest.getDeviceToken(), productionMode, pushPayload);
    }

    private String sign(PushMessage pushMessage, Integer platform) {
        String httpMethod = HttpMethod.POST.name();
        String postBody = JsonUtil.toJson(pushMessage);
        String sign= String.format("%s%s%s%s", httpMethod, umengIosPatientProperties.getUrl(), postBody, umengIosPatientProperties.getMasterSecret());
        LOGGER.info("ios sign => [{}]", sign);
        return DigestUtils.md5DigestAsHex(sign.getBytes());
    }
}
