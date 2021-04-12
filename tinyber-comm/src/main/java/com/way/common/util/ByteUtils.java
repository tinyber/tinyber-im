package com.tiny.common.util;

import com.tiny.common.packet.Client;
import com.tiny.common.packet.ImSessionContext;
import org.tio.core.ChannelContext;

import java.io.*;

/**
 * @author by wangwei
 * @ClassName Utils
 * @Description TODO
 * @Date 2021/1/8 14:38
 */
public class ByteUtils {


    /**
     * 对象转数组
     * @param obj
     * @return
     */
    public static byte[] toByteArray (Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray ();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }

    /**
     * 数组转对象
     * @param bytes
     * @return
     */
    public static Object toObject (byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream (bytes);
            ObjectInputStream ois = new ObjectInputStream (bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return obj;
    }

    /**
     * 设置Client对象到ImSessionContext中
     * @param channelContext
     * @return
     * @author: wchao
     */
    public static Client setClient(ChannelContext channelContext) {
        ImSessionContext imSessionContext = (ImSessionContext)channelContext.get();
        Client client = imSessionContext.getClient();
        if (client == null) {
            client = new Client();
            client.setId(channelContext.getId());
            client.setIp(channelContext.getClientNode().getIp());
            client.setPort(channelContext.getClientNode().getPort());
            imSessionContext.setClient(client);
        }
        return client;
    }
}
