package com.tiny.common.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.tiny.common.constants.enums.ImStatusEnum;
import com.tiny.common.chat.ChatBody;
import com.tiny.common.packet.ImPacket;
import com.tiny.common.packet.ImSessionContext;
import com.tiny.common.packet.Signal;
import com.tiny.common.packet.User;
import com.tiny.common.packet.http.HttpConst;
import com.tiny.common.response.RespBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.utils.lock.SetWithLock;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class ChatKit {
	
	private static Logger log = LoggerFactory.getLogger(ChatKit.class);
	/**
	 * 转换为聊天消息结构;
	 * @param body
	 * @param channelContext
	 * @return
	 */
	public static ChatBody toChatBody(byte[] body, ChannelContext channelContext){
		ChatBody chatReqBody = parseChatBody(body);
		if(chatReqBody != null){
			if(chatReqBody.getSender() == null || "".equals(chatReqBody.getSender())){
				ImSessionContext imSessionContext = (ImSessionContext)channelContext.getAttribute();
				User user = imSessionContext.getClient().getUser();
				if(user != null){
					chatReqBody.setSender(user.getNick());
				}else{
					chatReqBody.setSender(channelContext.getId());
				}
			}
		}
		return chatReqBody;
	}
	/**
	 * 判断是否属于指定格式聊天消息;
	 * @param body
	 * @return
	 */
	private static ChatBody parseChatBody(byte[] body){
		if(body == null) {
			return null;
		}
		ChatBody chatReqBody = null;
		try{
			String text = new String(body, HttpConst.CHARSET_NAME);
		    chatReqBody = JsonUtil.parse(text,ChatBody.class);
			if(chatReqBody != null){
				if(ObjectUtil.isNull(chatReqBody.getTimestamp())) {
					chatReqBody.setTimestamp(System.currentTimeMillis());
				}
				chatReqBody.setMsId(UUID.randomUUID().toString());
				return chatReqBody;
			}
		}catch(Exception e){
			
		}
		return chatReqBody;
	}
	/**
	 * 判断是否属于指定格式聊天消息;
	 * @param packet
	 * @return
	 */
	public static ChatBody parseChatBody(String bodyStr){
		if(bodyStr == null) {
			return null;
		}
		try {
			return parseChatBody(bodyStr.getBytes(HttpConst.CHARSET_NAME));
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}
  /**
   * 聊天数据格式不正确响应包
   * @param chatBody
   * @param channelContext
   * @return
   * @throws Exception
   */
   public static ImPacket dataInCorrectRespPacket(ChannelContext channelContext) throws Exception{
	   RespBody chatDataInCorrectRespPacket = new RespBody(Signal.CONTACT, ImStatusEnum.C10002);
	   ImPacket respPacket = ImKit.convertRespPacket(chatDataInCorrectRespPacket, channelContext);
	   //respPacket.setStatus(ImStatusEnum.C10002);
	   return respPacket;
   }
   /**
    * 聊天发送成功响应包
    * @param chatBody
    * @param channelContext
    * @return
    * @throws Exception
    */
    public static ImPacket  sendSuccessRespPacket(ChannelContext channelContext) throws Exception{
 	   RespBody chatDataInCorrectRespPacket = new RespBody(Signal.CONTACT,ImStatusEnum.C10000);
 	   ImPacket respPacket = ImKit.convertRespPacket(chatDataInCorrectRespPacket, channelContext);
 	   //respPacket.setStatus(ImStatus.C10000);
 	   return respPacket;
    }
    /**
     * 聊天用户不在线响应包
     * @param chatBody
     * @param channelContext
     * @return
     * @throws Exception
     */
     public static ImPacket  offlineRespPacket(ChannelContext channelContext) throws Exception{
  	   RespBody chatDataInCorrectRespPacket = new RespBody(Signal.CONTACT,ImStatusEnum.C10001);
  	   ImPacket respPacket = ImKit.convertRespPacket(chatDataInCorrectRespPacket, channelContext);
  	   //respPacket.setStatus(ImStatus.C10001);
  	   return respPacket;
     }
     /**
      * 判断用户是否在线;
      * @param userid
      * @return
      */
     public static boolean isOnline(ChannelContext channelContext,String userid){
    	 SetWithLock<ChannelContext> toChannleContexts = Tio.getByUserid(channelContext.tioConfig,userid);
    	 if(toChannleContexts != null && toChannleContexts.size() > 0){
    		 return true;
    	 }
    	 return false;
     }
     /**
      * 获取双方会话ID(算法,from与to相与的值通过MD5加密得出)
      * @param from
      * @param to
      * @return
      */
     public static String sessionId(String from , String to){
    	 String sessionId = "";
    	 try{
	    	 byte[] fbytes = from.getBytes(StandardCharsets.UTF_8);
	    	 byte[] tbytes = to.getBytes(StandardCharsets.UTF_8);
	    	 boolean isfmax = fbytes.length > tbytes.length;
	    	 boolean isequal = fbytes.length == tbytes.length;
	    	 if(isfmax){
	    		 for(int i = 0 ; i < fbytes.length ; i++){
		    		 for(int j = 0 ; j < tbytes.length ; j++){
		    			 fbytes[i] = (byte) (fbytes[i]^tbytes[j]);
		    		 }
		    	 }
	    		 sessionId = new String(fbytes);
	    	 }else if(isequal){
	    		 for(int i = 0 ; i < fbytes.length ; i++){
		    		  fbytes[i] = (byte) (fbytes[i]^tbytes[i]);
		    	 }
	    		 sessionId = new String(fbytes);
	    	 }else{
	    		 for(int i = 0 ; i < tbytes.length ; i++){
		    		 for(int j = 0 ; j < fbytes.length ; j++){
		    			 tbytes[i] = (byte) (tbytes[i]^fbytes[j]);
		    		 }
		    	 }
	    		 sessionId = new String(tbytes);
	    	 }
    	 }catch (Exception e) {
			log.error(e.toString(),e);
    	 }
    	 return SecureUtil.md5(sessionId);
     }
}