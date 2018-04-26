package com.springboot.config;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import com.springboot.properties.RedisProperties;

/**
 * @author liuyi
 *
 * @Date 2018年1月30日
 */
@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class JedisClusterConfig {

	@Autowired
	private RedisProperties redisProperties;

	@Autowired
	private GenericObjectPoolConfig genericObjectPoolConfig;

	@Bean
	public JedisCluster getJedisCluster() {
		String[] serverArray = redisProperties.getClusterNodes().split(",");
		Set<HostAndPort> nodes = new HashSet<>();

		for (String ipPort : serverArray) {
			String[] ipPortPair = ipPort.split(":");
			nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
		}
		return new JedisCluster(nodes, redisProperties.getConnectionTimeout(), redisProperties.getSoTimtout(), redisProperties.getMaxAttempts(), genericObjectPoolConfig);
	}
}
