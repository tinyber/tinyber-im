package com.tiny.common.config;

import com.tiny.common.packet.http.HttpConfig;
import com.tiny.common.packet.ws.WsConfig;
import org.tio.core.TioConfig;

/**
 * @author by wangwei
 * @ClassName ImConfig
 * @Description TODO
 * @Date 2020/11/4 14:18
 */
public class ImConfig {

    private String bindIp = null;

    /**
     * 监听端口
     */
    private Integer bindPort = 80;
    /**
     * 心跳包发送时长heartbeatTimeout/2
     */
    private long heartbeatTimeout = 0;
    /**
     * http相关配置;
     */
    private HttpConfig httpConfig;
    /**
     * websocket相关配置;
     */
    private WsConfig wsServerConfig;
    /**
     * 全局群组上下文;
     */
    public static TioConfig tioConfig;
    /**
     *  默认的接收数据的buffer size
     */
    private long readBufferSize = 1024 * 1024;


    public ImConfig(String bindIp, Integer bindPort){
        this.bindIp = bindIp;
        this.bindPort = bindPort;
    }
    public String getBindIp() {
        return bindIp;
    }
    public void setBindIp(String bindIp) {
        this.bindIp = bindIp;
    }
    public Integer getBindPort() {
        return bindPort;
    }
    public void setBindPort(Integer bindPort) {
        this.bindPort = bindPort;
    }
    public long getHeartbeatTimeout() {
        return heartbeatTimeout;
    }
    public void setHeartbeatTimeout(long heartbeatTimeout) {
        this.heartbeatTimeout = heartbeatTimeout;
    }
    public HttpConfig getHttpConfig() {
        return httpConfig;
    }
    public void setHttpConfig(HttpConfig httpConfig) {
        this.httpConfig = httpConfig;
    }
    public WsConfig getWsServerConfig() {
        return wsServerConfig;
    }
    public void setWsServerConfig(WsConfig wsServerConfig) {
        this.wsServerConfig = wsServerConfig;
    }
    public long getReadBufferSize() {
        return readBufferSize;
    }
    public void setReadBufferSize(long readBufferSize) {
        this.readBufferSize = readBufferSize;
    }
}
