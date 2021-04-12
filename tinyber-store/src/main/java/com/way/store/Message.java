package com.tiny.store;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tinyber_message")
public class Message implements Serializable {

    public static final transient String TABLE_NAME = "tinyber_message";

    private static final transient long serialVersionUID = 1845362556725768545L;

    public static final transient String STATE_NOT_RECEIVED = "0";

    public static final transient String STATE_RECEIVED = "1";

    public static final transient String STATE_READ = "2";

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "sender", length = 64, nullable = false)
    private String sender;

    @Column(name = "receiver", length = 64, nullable = false)
    private String receiver;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "content", length = 3200)
    private String content;

    @Column(name = "extra", length = 1000)
    private String extra;

    @Column(name = "action", nullable = false)
    private String action;

    @Column(name = "state", length = 2, nullable = false)
    private String state = "0";

    @Column(name = "format", length = 2)
    private String format = "0";

    @Column(name = "timestamp", updatable = false, length = 13)
    private Long timestamp = System.currentTimeMillis();

    public String getExtra() {
        return this.extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getSender() {
        return this.sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return this.receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFormat() {
        return this.format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @JsonIgnore
    public boolean isActionMessage() {
        return this.action.startsWith("9");
    }
}

