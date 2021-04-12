package com.tiny.store;


import java.util.List;

public class BaseResult {

  private int code = 200;
  
  private String message;
  
  private Object data;
  
  private List<?> dataList;
  
  private Page page;
  
  private String token;
  
  public int getCode() {
    return this.code;
  }
  
  public void setCode(int code) {
    this.code = code;
  }
  
  public String getMessage() {
    return this.message;
  }
  
  public void setMessage(String message) {
    this.message = message;
  }
  
  public Object getData() {
    return this.data;
  }
  
  public void setData(Object data) {
    this.data = data;
  }
  
  public List<?> getDataList() {
    return this.dataList;
  }
  
  public void setDataList(List<?> dataList) {
    this.dataList = dataList;
  }
  
  public Page getPage() {
    return this.page;
  }
  
  public void setPage(Page page) {
    this.page = page;
  }
  
  public String getToken() {
    return this.token;
  }
  
  public void setToken(String token) {
    this.token = token;
  }
  
  public static BaseResult make() {
    return new BaseResult();
  }
  
  public static BaseResult make(int code) {
    BaseResult result = new BaseResult();
    result.setCode(code);
    return result;
  }
  
  public static BaseResult makeData(Object data) {
    BaseResult result = new BaseResult();
    result.setData(data);
    return result;
  }
  
  public static BaseResult makeDataList(List<?> dataList) {
    BaseResult result = new BaseResult();
    result.setDataList(dataList);
    return result;
  }
}
