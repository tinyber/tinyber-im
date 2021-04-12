package com.tiny.store;

public class Page {
  private long count;
  
  private int size;
  
  private int currentPage;
  
  private int countPage;
  
  public long getCount() {
    return this.count;
  }
  
  public void setCount(long count) {
    this.count = count;
  }
  
  public int getSize() {
    return this.size;
  }
  
  public void setSize(int size) {
    this.size = size;
  }
  
  public int getCurrentPage() {
    return this.currentPage;
  }
  
  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }
  
  public int getCountPage() {
    return this.countPage;
  }
  
  public void setCountPage(int countPage) {
    this.countPage = countPage;
  }
}
