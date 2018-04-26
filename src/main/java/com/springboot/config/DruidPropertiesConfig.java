package com.springboot.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.springboot.properties.DruidProperties;

/**
 * @author liuyi
 *
 * @Date 2018年1月30日
 */
@Configuration
@EnableConfigurationProperties(DruidProperties.class)
public class DruidPropertiesConfig {

}
