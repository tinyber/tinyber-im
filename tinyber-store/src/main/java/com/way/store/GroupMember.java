package com.tiny.store;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_lvxin_group_member")
public class GroupMember implements Serializable {
  public static final transient String RULE_FOUNDER = "1";
  
  public static final transient String RULE_NORMAL = "0";
  
  private static final transient long serialVersionUID = 4733464888738356502L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  
  @Column(name = "groupId", length = 10, nullable = false)
  private long groupId;
  
  @Column(name = "account", length = 32, nullable = false)
  private String account;
  
  @Column(name = "host", nullable = false)
  private String host;
  
  @Column(name = "timestamp", length = 13, nullable = false)
  private Long timestamp;
  
  public Long getTimestamp() {
    return this.timestamp;
  }
  
  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }
  
  public Long getId() {
    return this.id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public void setGroupId(long groupId) {
    this.groupId = groupId;
  }
  
  public Long getGroupId() {
    return Long.valueOf(this.groupId);
  }
  
  public void setGroupId(Long groupId) {
    this.groupId = groupId.longValue();
  }
  
  public String getAccount() {
    return this.account;
  }
  
  public void setAccount(String account) {
    this.account = account;
  }
  
  public String getHost() {
    return this.host;
  }
  
  public void setHost(String host) {
    this.host = host;
  }
}
