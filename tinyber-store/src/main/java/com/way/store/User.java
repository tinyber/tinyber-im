package com.tiny.store;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tinyber_user")
public class User implements Serializable {
  private static final transient long serialVersionUID = 4733464888738356502L;
  
  public static final transient String TABLE_NAME = "tinyber_user";
  
  public static final transient String STATE_NORMAL = "0";
  
  public static final transient String STATE_DISABLE = "1";
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  
  @Column(name = "account", length = 32, unique = true, nullable = false)
  private String account;
  
  @Column(name = "password", length = 64, nullable = false)
  @JsonIgnore
  private String password;
  
  @Column(name = "name", length = 16, nullable = false)
  private String name;
  
  @Column(name = "telephone", length = 20)
  private String telephone;
  
  @Column(name = "email", length = 50)
  private String email;
  
  @Column(name = "code", length = 32)
  private String code;
  
  @Column(name = "gender", length = 1)
  private String gender;
  
  @Column(name = "grade")
  private Integer grade;
  
  @Column(name = "motto", length = 200)
  private String motto;
  
  @Column(name = "feature", length = 32)
  private String feature;
  
  @Column(name = "state", length = 1, nullable = false)
  private String state;
  
  @Column(name = "timestamp", length = 13, nullable = false)
  private Long timestamp;
  
  public Long getId() {
    return this.id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public String getMotto() {
    return this.motto;
  }
  
  public void setMotto(String motto) {
    this.motto = motto;
  }
  
  public String getAccount() {
    return this.account;
  }
  
  public Integer getGrade() {
    return this.grade;
  }
  
  public void setGrade(Integer grade) {
    this.grade = grade;
  }
  
  public String getEmail() {
    return this.email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public void setAccount(String account) {
    this.account = account;
  }
  
  public String getFeature() {
    return this.feature;
  }
  
  public void setFeature(String feature) {
    this.feature = feature;
  }
  
  public String getPassword() {
    return this.password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public String getName() {
    return this.name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getTelephone() {
    return this.telephone;
  }
  
  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }
  
  public Long getTimestamp() {
    return this.timestamp;
  }
  
  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }
  
  public String getCode() {
    return this.code;
  }
  
  public void setCode(String code) {
    this.code = code;
  }
  
  public String getGender() {
    return this.gender;
  }
  
  public void setGender(String gender) {
    this.gender = gender;
  }
  
  public String getState() {
    return this.state;
  }
  
  public void setState(String state) {
    this.state = state;
  }
  
  public boolean isDisabled() {
    return "1".equals(this.state);
  }
  
  public String toString() {
    StringBuilder buffer = new StringBuilder();
    buffer.append("{");
    buffer.append("id:").append("'").append((this.id == null) ? "" : this.id).append("'");
    buffer.append(",").append("account:").append("'").append((this.account == null) ? "" : this.account).append("'");
    buffer.append(",").append("name:").append("'").append((this.name == null) ? "" : this.name).append("'");
    buffer.append(",").append("telephone:").append("'").append((this.telephone == null) ? "" : this.telephone).append("'");
    buffer.append(",").append("code:").append("'").append((this.code == null) ? "" : this.code).append("'");
    buffer.append(",").append("gender:").append("'").append((this.gender == null) ? "" : this.gender).append("'");
    buffer.append(",").append("email:").append("'").append((this.email == null) ? "" : this.email).append("'");
    buffer.append("}");
    return buffer.toString();
  }
}
