package com.tiny.store;


import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "t_lvxin_group")
public class Group implements Serializable {
  private static final transient long serialVersionUID = 4733464888738356502L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  
  @Column(name = "name", length = 16, nullable = false)
  private String name;
  
  @Column(name = "summary", length = 200)
  private String summary;
  
  @Column(name = "category", length = 16)
  private String category;
  
  @Column(name = "founder", length = 32, nullable = false)
  private String founder;
  
  @Column(name = "timestamp", length = 13, nullable = false)
  private Long timestamp;
  
  @Transient
  private List<GroupMember> memberList;
  
  public Long getId() {
    return this.id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public String getSummary() {
    return this.summary;
  }
  
  public void setSummary(String summary) {
    this.summary = summary;
  }
  
  public String getName() {
    return this.name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getCategory() {
    return this.category;
  }
  
  public void setCategory(String category) {
    this.category = category;
  }
  
  public String getFounder() {
    return this.founder;
  }
  
  public void setFounder(String founder) {
    this.founder = founder;
  }
  
  public Long getTimestamp() {
    return this.timestamp;
  }
  
  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }
  
  public List<GroupMember> getMemberList() {
    return this.memberList;
  }
  
  public void setMemberList(List<GroupMember> memberList) {
    this.memberList = memberList;
  }
}
