package com.tiny.chat.start;

import com.tiny.chat.constant.ImConstant;
import com.tiny.chat.listen.DefaultListener;
import com.tiny.chat.server.handler.tcp.TcpServerHandler;
import org.tio.server.ServerTioConfig;
import org.tio.server.TioServer;
import org.tio.server.intf.ServerAioHandler;
import org.tio.server.intf.ServerAioListener;

import java.io.IOException;

/**
 * tcp 启动器
 * @author Administrator
 */
public class TcpServerStarter {

    /**
     * handler, 包括编码、解码、消息处理
     */
    public static ServerAioHandler aioHandler = new TcpServerHandler();

    /**
     * 事件监听器，可以为null，但建议自己实现该接口，可以参考showcase了解些接口
     */
    public static ServerAioListener aioListener = new DefaultListener();

    /**
     * 一组连接共用的上下文对象
     */
    public static ServerTioConfig serverTioConfig = new ServerTioConfig("tcp-im", aioHandler, aioListener);

    /**
     * tioServer对象
     */
    public static TioServer tioServer = new TioServer(serverTioConfig);

    /**
     * 有时候需要绑定ip，不需要则null
     */
    public static String serverIp = null;

    /**
     * 有时候需要绑定ip，不需要则null
     */
    public static int serverPort = ImConstant.PORT;

    /**
     * 启动程序入口
     */
    public  void startup() throws IOException {
        serverTioConfig.setHeartbeatTimeout(ImConstant.TIMEOUT);
        tioServer.start(serverIp, serverPort);
    }
}
