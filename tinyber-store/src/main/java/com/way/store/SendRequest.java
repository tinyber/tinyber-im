package com.tiny.store;

/**
 * 消息结构采用TLV格式(Tag,Length,Value)
 * Tag	    Length	    Value
 * 1个字节	2个字节	Length字节消息体
 * 无论是服务端写入或者客户端写入任何消息，其中消息头在一个消息的最前面占3位，
 * 第一位消息类型，第二和第三位消息的byte[]长度
 */
public class SendRequest {
    /**
     * length转换2位byte数组
     * @param length
     * @return
     */
    private byte[] createLengthHeader(int length){
        byte[] header = new byte[2];
        header[0] = (byte) (length & 0xff);
        header[1] = (byte) ((length >> 8) & 0xff);
        return header;
    }

    /**
     * 2位byte数组转消息体长度
     * @param lv
     * @param hv
     * @return
     */
    private int getContentLength(byte lv,byte hv){
        int l =  (lv & 0xff);
        int h =  (hv & 0xff);
        return (l| h << 8);
    }

    private byte [] body;

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        int length = body.length;
        byte[] data= new byte[length+3];
        data[0] = 1;
        byte[] header = createLengthHeader(length);
        data[1] = header[0];
        data[2] = header[1];
        System.arraycopy(body,0,data,3,length);
        this.body = body;
    }
}
