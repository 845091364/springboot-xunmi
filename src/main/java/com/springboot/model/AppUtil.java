package com.springboot.model;

import java.io.Serializable;

public class AppUtil
  implements Serializable
{
  private static final long serialVersionUID = -4260400863752740176L;
  private int queryid;
  private String data;
  private String appVersion;
  private String device;
  
  public int getQueryid()
  {
    return this.queryid;
  }
  
  public void setQueryid(int queryid)
  {
    this.queryid = queryid;
  }
  
  public String getData()
  {
    return this.data;
  }
  
  public void setData(String data)
  {
    this.data = data;
  }
  
  public String getAppVersion()
  {
    return this.appVersion;
  }
  
  public void setAppVersion(String appVersion)
  {
    this.appVersion = appVersion;
  }
  
  public String getDevice()
  {
    return this.device;
  }
  
  public void setDevice(String device)
  {
    this.device = device;
  }
  
  public AppUtil(int queryid, String data)
  {
    this.queryid = queryid;
    this.data = data;
  }
  
  public AppUtil() {}
}
