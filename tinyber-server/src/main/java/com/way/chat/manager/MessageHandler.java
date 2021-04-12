package com.tiny.chat.manager;

import com.tiny.common.packet.Signal;
import com.tiny.common.packet.SubSignal;
import com.tiny.chat.annotation.Handler;
import com.tiny.chat.util.ClassUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public  class MessageHandler {

    private final  static  Logger logger = LoggerFactory.getLogger(MessageHandler.class);

    private final Map<String, WsImHandler> wsHandlers = new HashMap<>();

    private static MessageHandler wsMessageHandler;

    public static MessageHandler getInstance() {
        if (wsMessageHandler == null) {
            synchronized (MessageHandler.class) {
                if (wsMessageHandler == null) {
                    wsMessageHandler = new MessageHandler();
                }
            }
        }
        return wsMessageHandler;
    }

    public MessageHandler() {
        registerAllAction();
    }

    public byte[] handleRequest(Signal signal, SubSignal subSignal, String content) {

        WsImHandler wsImHandler = wsHandlers.get(signal.name());
        if (wsImHandler == null) {
            wsImHandler = wsHandlers.get(subSignal.name());
        }
        if (wsImHandler != null) {
            return wsImHandler.handleRequest(signal, subSignal, content);
        } else {
            return null;
        }
    }

    public String handleResult(Signal signal, SubSignal subSignal, byte[] body) {
        WsImHandler wsImHandler = wsHandlers.get(signal.name());
        if (wsImHandler == null) {
            wsImHandler = wsHandlers.get(subSignal.name());
        }
        if (wsImHandler != null) {
            return wsImHandler.handleResult(signal, subSignal, body);
        } else {
            return null;
        }
    }

    private void registerAllAction() {
        try {
            for (Class cls : ClassUtil.getAllAssignedClass(WsImHandler.class)) {
                Handler annotation = (Handler) cls.getAnnotation(Handler.class);
                if (annotation != null) {
                    WsImHandler handler = (WsImHandler) cls.newInstance();
                    wsHandlers.put(annotation.value(), handler);
                }
            }
        } catch (Exception e) {
        }
    }
}
