/*


package com.tiny.chat.server.handler.http;

import com.tiny.chat.server.handler.AbstractServerHandler;
import com.tiny.common.packet.ImPacket;
import com.tiny.common.packet.http.*;
import com.tiny.common.packet.http.handler.HttpRequestHandler;
import com.tiny.common.session.id.impl.UUIDSessionIdGenerator;
import com.tiny.chat.config.ImConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.utils.cache.guava.GuavaCache;

import java.nio.ByteBuffer;
import java.util.Objects;

public class HttpServerHandler extends AbstractServerHandler {

    private Logger log = LoggerFactory.getLogger(HttpServerHandler.class);

    private HttpConfig httpConfig;

    private HttpRequestHandler httpRequestHandler;

    public HttpServerHandler(){
        this(null, new HttpServerHandler(new HttpConvertPacket()));
    };

    public HttpServerHandler(HttpConfig httpConfig, AbstractProtocol protocol){
        super(protocol);
        this.httpConfig = httpConfig;
    }

    @Override
    public void init(ImServerConfig imServerConfig)throws ImException {
        this.httpConfig = imServerConfig.getHttpConfig();
        if (Objects.isNull(httpConfig.getSessionStore())) {
            GuavaCache guavaCache = GuavaCache.register(httpConfig.getSessionCacheName(), null, httpConfig.getSessionTimeout());
            httpConfig.setSessionStore(guavaCache);
        }
        if (Objects.isNull(httpConfig.getSessionIdGenerator())) {
            httpConfig.setSessionIdGenerator(UUIDSessionIdGenerator.instance);
        }
        if(Objects.isNull(httpConfig.getScanPackages())){
            //J-IM MVC需要扫描的根目录包
            String[] scanPackages = new String[] { JimServer.class.getPackage().getName() };
            httpConfig.setScanPackages(scanPackages);
        }else{
            String[] scanPackages = new String[httpConfig.getScanPackages().length+1];
            scanPackages[0] = JimServer.class.getPackage().getName();
            System.arraycopy(httpConfig.getScanPackages(), 0, scanPackages, 1, httpConfig.getScanPackages().length);
            httpConfig.setScanPackages(scanPackages);
        }
        Routes routes = new Routes(httpConfig.getScanPackages());
        httpRequestHandler = new DefaultHttpRequestHandler(httpConfig, routes);
        httpConfig.setHttpRequestHandler(httpRequestHandler);
        log.info("Http Protocol initialized");
    }

    @Override
    public ByteBuffer encode(ImPacket imPacket, ImConfig imConfig, ImChannelContext imChannelContext) {
        HttpResponse httpResponsePacket = (HttpResponse) imPacket;
        ByteBuffer byteBuffer = HttpResponseEncoder.encode(httpResponsePacket, imChannelContext,false);
        return byteBuffer;
    }

    @Override
    public void handler(ImPacket imPacket, ImChannelContext imChannelContext)throws ImException {
        HttpRequest httpRequestPacket = (HttpRequest) imPacket;
        HttpResponse httpResponsePacket = httpRequestHandler.handler(httpRequestPacket, httpRequestPacket.getRequestLine());
        JimServerAPI.send(imChannelContext, httpResponsePacket);
    }

    @Override
    public ImPacket decode(ByteBuffer buffer, int limit, int position, int readableLength, ImChannelContext imChannelContext)throws ImDecodeException {
        HttpRequest request = HttpRequestDecoder.decode(buffer, imChannelContext,true);
        imChannelContext.set(ImConst.HTTP_REQUEST,request);
        return request;
    }

    public IHttpRequestHandler getHttpRequestHandler() {
        return httpRequestHandler;
    }

    public void setHttpRequestHandler(IHttpRequestHandler httpRequestHandler) {
        this.httpRequestHandler = httpRequestHandler;
    }

    public HttpConfig getHttpConfig() {
        return httpConfig;
    }

    public void setHttpConfig(HttpConfig httpConfig) {
        this.httpConfig = httpConfig;
    }

}
*/
