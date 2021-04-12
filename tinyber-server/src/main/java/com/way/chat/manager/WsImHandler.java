package com.tiny.chat.manager;

import com.google.protobuf.GeneratedMessage;
import com.tiny.common.packet.Signal;
import com.tiny.common.packet.SubSignal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.utils.json.Json;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;


/**
 * @author way
 */
public abstract class WsImHandler<T, V> {


    protected static final Logger log = LoggerFactory.getLogger(WsImHandler.class);

    private Class requestCls;

    private Class resultCls;

    private Method parseDataMethod;

    public WsImHandler() {
        try {

            Type superclass = this.getClass().getGenericSuperclass();
            ParameterizedType parameterizedType = (ParameterizedType) superclass;
            Type requestType = parameterizedType.getActualTypeArguments()[0];
            if ("java.util.ArrayList<java.lang.String>".equals(requestType.getTypeName())) {
                requestCls = Class.forName("java.util.ArrayList");
            } else {
                requestCls = (Class) requestType;
            }
            resultCls = (Class) parameterizedType.getActualTypeArguments()[1];
            log.info("request type {} resultCls {}", requestCls, resultCls);
            if (resultCls != null && resultCls.getSuperclass() != null && resultCls.getSuperclass().equals(GeneratedMessage.class)) {
                parseDataMethod = resultCls.getMethod("parseFrom", byte[].class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] handleRequest(Signal signal, SubSignal subSignal, String content) {
        byte[] result = null;
        try {
            T request = null;
            if (requestCls != String.class) {
                request = (T) Json.toBean(content, requestCls);
            } else {
                request = (T) content;
            }
            result = request(signal, subSignal, request);
        } catch (Exception e) {
        }
        return result;
    }


    public String handleResult(Signal signal, SubSignal subSignal, byte[] body) {
        String result = "";
        try {
            V resultObj = null;

            if (body != null) {
                ByteBuffer byteBuffer = ByteBuffer.wrap(body);
                if (signal == Signal.PUB_ACK) {
                    byte code = byteBuffer.get();
                    log.info("PUB_ACK {} result code {}", subSignal, code);
                    if (code == 0) {
                        if (parseDataMethod != null) {
                            byte[] remainBytes = new byte[byteBuffer.remaining()];
                            byteBuffer.get(remainBytes);
                            resultObj = (V) parseDataMethod.invoke(resultCls, remainBytes);
                        }
                        if (resultCls == ByteBuffer.class) {
                            resultObj = (V) byteBuffer;
                        }
                    }
                    if (resultCls == Byte.class) {
                        Byte b = code;
                        resultObj = (V) b;
                    }
                } else if (signal == Signal.PUBLISH) {
                    if (parseDataMethod != null) {
                        resultObj = (V) parseDataMethod.invoke(resultCls, byteBuffer.array());
                    }
                    if (resultCls == ByteBuffer.class) {
                        resultObj = (V) byteBuffer;
                    }
                } else if (signal == Signal.CONNECT_ACK) {
                    if (parseDataMethod != null) {
                        resultObj = (V) parseDataMethod.invoke(resultCls, byteBuffer.array());
                    }
                }
            }

            if (resultObj != null) {
                result = result(signal, subSignal, resultObj);
            }

        } catch (Exception e) {
        }

        return result;
    }

    /*protected String byteResult(Byte result) {
        WsResult wsResult = null;
        if (result == 0) {
            wsResult = WsResult.error(WsResult.RestCode.SUCCESS);
        } else {
            wsResult = WsResult.error(WsResult.RestCode.ERROR);
        }
        return Json.toJson(wsResult);
    }*/

    /**
     * 所有的抽象函数字段必须包含泛型,这样才能解析出原始类型
     */
    abstract public byte[] request(Signal signal, SubSignal subSignal, T request);


    abstract public String result(Signal signal, SubSignal subSignal, V result);


}
