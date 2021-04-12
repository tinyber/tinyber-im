package com.tiny.chat.proess;

import com.tiny.common.constants.enums.ImStatusEnum;
import com.tiny.common.packet.ImPacket;
import com.tiny.common.packet.Signal;
import com.tiny.common.response.RespBody;
import com.tiny.common.util.ImKit;
import com.tiny.chat.signal.AbstractSignalHandler;
import org.tio.core.ChannelContext;

/**
 * @author way
 */
public class AuthReqHandler extends AbstractSignalHandler {

    @Override
    public ImPacket handler(ImPacket packet, ChannelContext channelContext) throws Exception {
        if (packet.getBody() == null) {
            RespBody respBody = new RespBody(Signal.AUTH, ImStatusEnum.C10010);
            return ImKit.convertRespPacket(respBody, channelContext);
        }
		/*AuthReqBody authReqBody = JsonKit.toBean(packet.getBody(), AuthReqBody.class);
		String token = authReqBody.getToken() == null ? "" : authReqBody.getToken();
		String data = token +  Const.authkey;
		authReqBody.setToken(data);
		authReqBody.setCmd(null);*/
        RespBody respBody = new RespBody(Signal.AUTH, ImStatusEnum.C10009);
        return ImKit.convertRespPacket(respBody, channelContext);
    }

    @Override
    public Signal signal() {
        return Signal.AUTH;
    }
}