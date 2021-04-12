package com.tiny.chat.config;

import com.tiny.common.config.ImConfig;
import com.tiny.common.packet.http.HttpConfig;
import com.tiny.common.packet.ws.WsConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ssl.SslConfig;
import org.tio.server.ServerTioConfig;

import java.util.Objects;

/**
 * @ClassName ImServerConfig
 * @Description TODO
 * @Author WChao
 * @Date 2020/1/4 10:40
 * @Version 1.0
 **/
public class ImServerConfig extends ImConfig {

    private static Logger log = LoggerFactory.getLogger(ImServerConfig.class);
    /**
     * 服务端消息处理器
     */
    private ImServerHandler imServerHandler;
    /**
     * 服务端消息监听器
     */
    private ImServerListener imServerListener;
    /**
     * http相关配置;
     */
    private HttpConfig httpConfig;
    /**
     * WebSocket相关配置;
     */
    private WsConfig wsConfig;
    /**
     * 开启
     */
    public static String ON = "on";
    /**
     * 关闭
     */
    public static String OFF = "off";
    /**
     * 是否开启持久化;
     */
    private String isStore = OFF;
    /**
     * 是否开启集群;
     */
    private String isCluster = OFF;
    /**
     * 是否开启SSL加密
     */
    private String isSSL = OFF;

    private ImServerConfig(ImServerHandler imServerHandler, ImServerListener imServerListener){
        setImServerHandler(imServerHandler);
        setImServerListener(imServerListener);
        this.tioConfig  = new ServerTioConfig(this.getName(), new ImServerHandlerAdapter(this.imServerHandler),new ImServerListenerAdapter(this.imServerListener));
        ImConfig.Global.set(this);
    }

    @Override
    public ImHandler getImHandler() {
        return getImServerHandler();
    }

    @Override
    public ImListener getImListener() {
        return getImServerListener();
    }


    public ImServerListener getImServerListener() {
        return imServerListener;
    }

    public void setImServerHandler(ImServerHandler imServerHandler) {
        this.imServerHandler = imServerHandler;
        if(Objects.isNull(this.imServerHandler)){
            this.imServerHandler = new DefaultImServerHandler();
        }
    }

    public void setImServerListener(ImServerListener imServerListener) {
        this.imServerListener = imServerListener;
        if(Objects.isNull(this.imServerListener)){
            this.imServerListener = new DefaultImServerListener();
        }
    }

    public MessageHelper getMessageHelper() {
        return messageHelper;
    }

    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }

    public String getIsStore() {
        return isStore;
    }

    public void setIsStore(String isStore) {
        this.isStore = isStore;
    }

    public String getIsCluster() {
        return isCluster;
    }

    /**
     * 设置是否开启集群
     * @param isCluster
     */
    public void setIsCluster(String isCluster) {
        this.isCluster = isCluster;
    }

    public String getIsSSL() {
        return isSSL;
    }

    public void setIsSSL(String isSSL) {
        this.isSSL = isSSL;
    }

    public SslConfig getSslConfig() {
        return sslConfig;
    }

    public void setSslConfig(SslConfig sslConfig) {
        this.sslConfig = sslConfig;
        this.tioConfig.setSslConfig(sslConfig);
    }

    public HttpConfig getHttpConfig() {
        return httpConfig;
    }

    public void setHttpConfig(HttpConfig httpConfig) {
        this.httpConfig = httpConfig;
    }

    public WsConfig getWsConfig() {
        return wsConfig;
    }

    public void setWsConfig(WsConfig wsConfig) {
        this.wsConfig = wsConfig;
    }

    public ImServerHandler getImServerHandler() {
        return imServerHandler;
    }
}
