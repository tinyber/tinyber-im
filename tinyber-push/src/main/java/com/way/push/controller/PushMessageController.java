package com.tiny.push.controller;

import com.tiny.push.push.PushBizService;
import com.tiny.push.domain.PushRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



/**
 * @Description:
 * @Author wangwei
 * @Date 2020/4/26 16:47
 */
@RestController
public class PushMessageController {

    private final PushBizService pushBizService;

    public PushMessageController(PushBizService pushBizService) {
        this.pushBizService = pushBizService;
    }

    @PostMapping("/push/message")
    public com.tiny.common.response.Result<Boolean> push(@RequestBody PushRequest pushRequest){

       return com.tiny.common.response.Result.success(pushBizService.pushMessage(pushRequest));
    }

    @PostMapping("/push/callback/{channel}")
    public com.tiny.common.response.Result<String> push(@PathVariable("channel") String channel){
        return com.tiny.common.response.Result.success();
    }
}
