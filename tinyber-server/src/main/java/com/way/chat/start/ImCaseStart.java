package com.tiny.chat.start;

import com.tiny.chat.server.handler.ws.WsMsgHandler;
import com.tiny.chat.ws.WsServerHandler;
import org.tio.server.ServerTioConfig;

/**
 * @author by wangwei
 * @ClassName ImCaseStart
 * @Description TODO
 * @Date 2021/1/6 16:28
 */
public class ImCaseStart {

    private WsServerStarter wsServerStarter;

    private ServerTioConfig serverTioConfig;

    public ImCaseStart(int port, WsMsgHandler wsMsgHandler) throws Exception {
        wsServerStarter = new WsServerStarter(port, wsMsgHandler);
        serverTioConfig = wsServerStarter.getServerTioConfig();
    }
    public static void start() throws Exception {
        ImCaseStart appStarter = new ImCaseStart(9876, WsServerHandler.me);
        appStarter.wsServerStarter.start();
    }
}
