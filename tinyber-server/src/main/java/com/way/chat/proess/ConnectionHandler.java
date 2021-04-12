package com.tiny.chat.proess;

import com.tiny.common.chat.ConnectRequest;
import com.tiny.common.packet.*;
import com.tiny.common.util.ByteUtils;
import com.tiny.common.util.JsonUtil;
import com.tiny.chat.annotation.Handler;
import com.tiny.chat.signal.AbstractSignalHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;

/**
 * 处理登陆请求
 *
 * @author Administrator
 */
@Handler("connection")
public class ConnectionHandler extends AbstractSignalHandler {

    private static final Logger log = LoggerFactory.getLogger(ConnectionHandler.class);

    @Override
    public Signal signal() {
        return Signal.CONNECT;
    }

    @Override
    public ImPacket handler(ImPacket packet, ChannelContext channelContext) throws Exception {
        log.info("收到登录请求消息:{}", JsonUtil.toJson("bsBody"));
        SubSignal subSignal = packet.getHeader().getSubSignal();
        ImSessionContext imSessionContext = (ImSessionContext) channelContext.get();
        ConnectRequest request=(ConnectRequest) ByteUtils.toObject(packet.getBody());
        //TODO 设置client
        Client client = ByteUtils.setClient(channelContext);
        //TODO 查询用户信息
        User user=new User();
        user.setId(request.getAccount());
        user.setAvatar("");
        user.setSign("");
        user.setTerminal("");
        user.setNick("");
        user.setStatus("");

        client.setDeviceModel("");
        client.setAccount("");
        client.setLatitude(0d);
        client.setLocation("");
        client.setLongitude(0d);
        client.setBindTime(System.currentTimeMillis());
        client.setChannel("");
        client.setState(0);
        client.setSystemVersion("");
        client.setUser(user);
        imSessionContext.setClient(client);
        //TODO 参数验证

        Tio.bindUser(channelContext, user.getId());
        ImPacket wxPacket=new ImPacket();
        wxPacket.setSignal(signal());
        wxPacket.setBody(ByteUtils.toByteArray(wxPacket));
        wxPacket.setSubSignal(subSignal);
        Tio.send(channelContext,wxPacket);
        return null;
    }
}
