package com.tiny.common.chat;

import java.util.Date;

/**
 * @author by wangwei
 * @ClassName HuangUpRequest
 * @Description TODO
 * @Date 2020/12/24 17:29
 * {
 * 	"callduration": "0",
 * 	"calltime": "2020-12-24 17:25:39",
 * 	"endtime": "2020-12-24 17:26:20",
 * 	"fromcid": "1342038714288840704",
 * 	"fromdevice": 3,
 * 	"fromipid": 1412827,
 * 	"fromuid": 37879,
 * 	"hanguptype": 9,
 * 	"id": "7576",
 * 	"respwait": "0",
 * 	"status": 4,
 * 	"streamwait": "0",
 * 	"tocid": "",
 * 	"touid": 34592,
 * 	"type": 1
 * }
 */
public class HuangUpRequest {

    private String callduration;
    private Date calltime;
    private Date endtime;
    private String fromcid;
    private int fromdevice;
    private long fromipid;
    private int fromuid;
    private int hanguptype;
    private String id;
    private String respwait;
    private int status;
    private String streamwait;
    private String tocid;
    private int touid;
    private int type;
}
