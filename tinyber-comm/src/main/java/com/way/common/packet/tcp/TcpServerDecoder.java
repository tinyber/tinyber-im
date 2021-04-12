package com.tiny.common.packet.tcp;

import com.tiny.common.constants.enums.ImStatusEnum;
import com.tiny.common.Protocol;
import com.tiny.common.packet.ImPacket;
import com.tiny.common.packet.Signal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.exception.AioDecodeException;

import java.nio.ByteBuffer;

public class TcpServerDecoder {

	private static final Logger logger = LoggerFactory.getLogger(TcpServerDecoder.class);

	public static TcpPacket decode(ByteBuffer buffer, ChannelContext channelContext) throws AioDecodeException {
		if(!isCompletePacket(buffer)) {
			return null;
		}
		//获取第一个字节协议版本号;
		byte version = buffer.get();
		if(version != Protocol.VERSION){
			throw new AioDecodeException(ImStatusEnum.C10013.getText());
		}
		//标志位
		byte maskByte = buffer.get();
		int synSeq = 0;
		if(ImPacket.decodeHasSynSeq(maskByte)){//同步发送;
			synSeq = buffer.getInt();
		}
		//cmd命令码
		byte cmdByte = buffer.get();
		if(Signal.toEnum(cmdByte) == null){
			throw new AioDecodeException(ImStatusEnum.C10014.getText());
		}
		int bodyLen = buffer.getInt();
		int readableLength = buffer.limit() - buffer.position();
		//数据不正确，则抛出AioDecodeException异常
		if (readableLength < bodyLen)
		{
			throw new AioDecodeException("bodyLength [" + bodyLen + "] is not right, remote:" + channelContext.getClientNode());
		}
		byte[] body = new byte[bodyLen];
		buffer.get(body,0,bodyLen);
		logger.info("TCP解码成功...");
		//bytebuffer的总长度是 = 1byte协议版本号+1byte消息标志位+4byte同步序列号(如果是同步发送则多4byte同步序列号,否则无4byte序列号)+1byte命令码+4byte消息的长度+消息体的长度
		TcpPacket tcpPacket = new TcpPacket();
		tcpPacket.setBody(body);
		tcpPacket.setSignal(Signal.toEnum(cmdByte));
		//tcpPacket.setVersion(version);
		//tcpPacket.setMask(maskByte);
		if(synSeq > 0){//同步发送设置同步序列号
			tcpPacket.setSynSeq(synSeq);
			try {
				channelContext.tioConfig.getAioHandler().handler(tcpPacket, channelContext);
			} catch (Exception e) {
				logger.error("同步发送解码调用handler异常!"+e);
			}
		}
		return tcpPacket;
	}
	/**
	 * 判断是否完整的包
	 * @param buffer
	 * @return
	 * @throws AioDecodeException
	 */
	private static boolean isCompletePacket(ByteBuffer buffer)throws AioDecodeException{
		//协议头索引;
		int index = 0;
		//获取第一个字节协议版本号;
		byte version = buffer.get(index);
		if(version != Protocol.VERSION){
			throw new AioDecodeException(ImStatusEnum.C10013.getText());
		}
		index++;
		//标志位
		byte maskByte = buffer.get(index);
		if(ImPacket.decodeHasSynSeq(maskByte)){//同步发送;
			index += 4;
		}
		index++;
		//cmd命令码
		byte cmdByte = buffer.get(index);
		if(Signal.toEnum(cmdByte) == null){
			throw new AioDecodeException(ImStatusEnum.C10014.getText());
		}
		index++;
		//消息体长度
		int bodyLen = buffer.getInt(index);
		index += 4;
		int readableLength = buffer.limit() - buffer.position() ;
		if (readableLength < bodyLen + index)
		{
			return false;
		}
		return true;
	}
}
