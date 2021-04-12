package com.tiny.chat;

import com.tiny.chat.start.ImCaseStart;
import com.tiny.chat.start.TcpServerStarter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author by wangwei
 * @ClassName ServerApplication
 * @Description TODO
 * @Date 2021/1/6 16:04
 */
@SpringBootApplication
public class ServerApplication {


    public static void main(String[] args) throws Exception {
        new TcpServerStarter().startup();
        ImCaseStart.start();
        SpringApplication.run(ServerApplication.class, args);
    }

}
