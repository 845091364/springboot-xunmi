package com.springboot.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springboot.properties.GenericPoolProperties;

/**
 * @author liuyi
 *
 * @Date 2018年1月30日
 */
@Configuration
@EnableConfigurationProperties(GenericPoolProperties.class)
public class RedisGenericPoolConfig {

	@Autowired
	private GenericPoolProperties propertis;

	@Bean
	public GenericObjectPoolConfig getGenericObjectPoolConfig() {
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		config.setMaxIdle(propertis.getMaxIdle());
		config.setMaxWaitMillis(propertis.getMaxWaitMillis());
		config.setMinIdle(propertis.getMinIdle());
		return config;
	}
}
