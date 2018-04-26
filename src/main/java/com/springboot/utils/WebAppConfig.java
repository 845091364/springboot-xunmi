package com.springboot.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WebAppConfig
{
  private static Properties properties = new Properties();
  private static WebAppConfig appConfig = null;
  private String path = "webapp.properties";
  
  private WebAppConfig()
  {
    InputStream in = WebAppConfig.class.getClassLoader().getResourceAsStream(this.path);
    try
    {
      properties.load(in);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  public static WebAppConfig getInstance()
  {
    if (appConfig == null) {
      appConfig = new WebAppConfig();
    }
    return appConfig;
  }
  
  public String getByKey(String key)
  {
    return (String)((String)properties.get(key) == null ? "" : properties.get(key));
  }
}
