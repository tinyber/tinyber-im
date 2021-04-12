/**
  * Copyright 2020 bejson.com
  */
package com.tiny.common.chat;

/**
 * {
 * 	"fromavatar": "/avatar/tio/ 20200708/4.jpg",
 * 	"fromcid": "1324003175744806912",
 * 	"fromdevice": 1,
 * 	"fromipid": 139365,
 * 	"fromnick": "samuelway",
 * 	"fromuid": 34592,
 * 	"id": "7",
 * 	"touid": 34592,
 * 	"type": 1
 * }
 */
public class JsonRootBean1 {

    private String fromAvatar;

    private String fromCid;

    private int fromDevice;

    private long fromPid;

    private String fromNick;

    private int fromUid;

    private String id;

    private int toUid;

    private int type;

    public String getFromAvatar() {
        return fromAvatar;
    }

    public void setFromAvatar(String fromAvatar) {
        this.fromAvatar = fromAvatar;
    }

    public String getFromCid() {
        return fromCid;
    }

    public void setFromCid(String fromCid) {
        this.fromCid = fromCid;
    }

    public int getFromDevice() {
        return fromDevice;
    }

    public void setFromDevice(int fromDevice) {
        this.fromDevice = fromDevice;
    }

    public long getFromPid() {
        return fromPid;
    }

    public void setFromPid(long fromPid) {
        this.fromPid = fromPid;
    }

    public String getFromNick() {
        return fromNick;
    }

    public void setFromNick(String fromNick) {
        this.fromNick = fromNick;
    }

    public int getFromUid() {
        return fromUid;
    }

    public void setFromUid(int fromUid) {
        this.fromUid = fromUid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getToUid() {
        return toUid;
    }

    public void setToUid(int toUid) {
        this.toUid = toUid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
