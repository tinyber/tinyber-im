package com.tiny.chat.start;

import com.tiny.common.packet.ws.IWsMsgHandler;
import com.tiny.chat.listen.DefaultListener;
import com.tiny.chat.ws.WsServerConfig;
import com.tiny.chat.ws.WsServerHandler;
import com.tiny.chat.ws.WsTioUuid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.intf.TioUuid;
import org.tio.server.ServerTioConfig;
import org.tio.server.TioServer;
import org.tio.server.intf.ServerAioHandler;
import org.tio.server.intf.ServerAioListener;
import org.tio.utils.Threads;
import org.tio.utils.thread.pool.SynThreadPoolExecutor;

import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

public class WsServerStarter {

    private static final Logger log = LoggerFactory.getLogger(WsServerStarter.class);

    private WsServerConfig wsServerConfig = null;

    private IWsMsgHandler wsMsgHandler = null;

    /**
     * handler, 包括编码、解码、消息处理
     */
    private ServerAioHandler wsServerAioHandler = new WsServerHandler();

    private ServerAioListener wsServerAioListener = null;

    private ServerTioConfig serverTioConfig = null;

    private TioServer tioServer = null;

    public TioServer getTioServer() {
        return tioServer;
    }

    /**
     * @return the wsServerConfig
     */
    public WsServerConfig getWsServerConfig() {
        return wsServerConfig;
    }

    /**
     * @return the wsMsgHandler
     */
    public IWsMsgHandler getWsMsgHandler() {
        return wsMsgHandler;
    }

    public ServerAioHandler getWsServerAioHandler() {
        return wsServerAioHandler;
    }

    public void setWsServerAioHandler(ServerAioHandler wsServerAioHandler) {
        this.wsServerAioHandler = wsServerAioHandler;
    }

    public ServerAioListener getWsServerAioListener() {
        return wsServerAioListener;
    }

    public void setWsServerAioListener(ServerAioListener wsServerAioListener) {
        this.wsServerAioListener = wsServerAioListener;
    }

    /**
     * @return the serverTioConfig
     */
    public ServerTioConfig getServerTioConfig() {
        return serverTioConfig;
    }

    public WsServerStarter(int port, IWsMsgHandler wsMsgHandler) throws IOException {
        this(port, wsMsgHandler, null, null);
    }

    public WsServerStarter(int port, IWsMsgHandler wsMsgHandler, SynThreadPoolExecutor tioExecutor, ThreadPoolExecutor groupExecutor) throws IOException {
        this(new WsServerConfig(port), wsMsgHandler, tioExecutor, groupExecutor);
    }

    public WsServerStarter(WsServerConfig wsServerConfig, IWsMsgHandler wsMsgHandler) throws IOException {
        this(wsServerConfig, wsMsgHandler, null, null);
    }

    public WsServerStarter(WsServerConfig wsServerConfig, IWsMsgHandler wsMsgHandler, SynThreadPoolExecutor tioExecutor, ThreadPoolExecutor groupExecutor) throws IOException {
        this(wsServerConfig, wsMsgHandler, new WsTioUuid(), tioExecutor, groupExecutor);
    }

    public WsServerStarter(WsServerConfig wsServerConfig, IWsMsgHandler wsMsgHandler, TioUuid tioUuid, SynThreadPoolExecutor tioExecutor, ThreadPoolExecutor groupExecutor)
            throws IOException {
        if (tioExecutor == null) {
            tioExecutor = Threads.getTioExecutor();
        }

        if (groupExecutor == null) {
            groupExecutor = Threads.getGroupExecutor();
        }

        this.wsServerConfig = wsServerConfig;
        this.wsMsgHandler = wsMsgHandler;
        wsServerAioHandler = new WsServerHandler(wsServerConfig, wsMsgHandler);
        wsServerAioListener = new DefaultListener();
        serverTioConfig = new ServerTioConfig("Tio Websocket Server", wsServerAioHandler, wsServerAioListener, tioExecutor, groupExecutor);
        serverTioConfig.setHeartbeatTimeout(0);
        serverTioConfig.setTioUuid(tioUuid);
        serverTioConfig.setReadBufferSize(1024 * 30);
        tioServer = new TioServer(serverTioConfig);
    }

    public void start() throws IOException {
        tioServer.start(wsServerConfig.getBindIp(), wsServerConfig.getBindPort());
    }
}
