package com.tiny.chat.proess;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import com.tiny.common.chat.ChatBody;
import com.tiny.common.packet.*;
import com.tiny.common.response.RespBody;
import com.tiny.common.util.ChatKit;
import com.tiny.chat.annotation.Handler;
import com.tiny.chat.signal.AbstractSignalHandler;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;

import java.util.List;

@Handler("contact")
public class ContactHandler extends AbstractSignalHandler {


    private static Cache<String, Cache<String, List<ImPacket>>> userChatCache = CacheUtil.newFIFOCache(500, 1000 * 60 * 60 * 24);

    private static Cache<String, Cache<String, List<ImPacket>>> senseChatCache = CacheUtil.newFIFOCache(500, 1000 * 60 * 60 * 24);


    @Override
    public Signal signal() {
        return Signal.CONTACT;
    }

    @Override
    public ImPacket handler(ImPacket packet, ChannelContext channelContext) throws Exception {
        if (packet.getBody() == null) {
            throw new Exception("body is null");
        }
        SubSignal subSignal = packet.getHeader().getSubSignal();
        ImSessionContext imSessionContext = (ImSessionContext) channelContext.get();
        Client client = imSessionContext.getClient();
        ChatBody chatBody = ChatKit.toChatBody(packet.getBody(), channelContext);
        //聊天数据格式不正确
        if (chatBody == null || chatBody.getChatType() == null) {
            return ChatKit.dataInCorrectRespPacket(channelContext);
        }
        //TODO 敏感词

        //TODO 处理聊天类型信令

        /*if(ChatType.forNumber(chatBody.getChatType()) != null){//异步调用业务处理消息接口
            MsgQueueRunnable msgQueueRunnable = (MsgQueueRunnable)channelContext.getAttribute(Const.CHAT_QUEUE);
            msgQueueRunnable.addMsg(packet);
            msgQueueRunnable.getExecutor().execute(msgQueueRunnable);
        }*/
        ImPacket chatPacket = new ImPacket(Signal.CONTACT, new RespBody(Signal.CONTACT, chatBody).toByte());
        //设置同步序列号;
        chatPacket.setSynSeq(packet.getSynSeq());
        //私聊
        if (0 == chatBody.getChatType()) {
            String toId = chatBody.getReceiver();
            if (ChatKit.isOnline(channelContext, toId)) {
                Tio.sendToUser(channelContext.tioConfig, toId, chatPacket);
                //发送成功响应包
                return ChatKit.sendSuccessRespPacket(channelContext);
            } else {
                //用户不在线响应包
                return ChatKit.offlineRespPacket(channelContext);
            }
            //群聊
        } else if (1 == chatBody.getChatType()) {
            String groupId = chatBody.getGroupId();
            Tio.sendToGroup(channelContext.tioConfig, groupId, chatPacket);
            //发送成功响应包
            return ChatKit.sendSuccessRespPacket(channelContext);
        }
        return null;
    }

}
