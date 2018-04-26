package com.springboot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author liuyi
 *
 * @Date 2018年1月30日
 */
@ConfigurationProperties(prefix = "redis.cluster")
public class RedisProperties {

	private String clusterNodes;

	private int soTimtout;

	private int maxAttempts;

	private int connectionTimeout;

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public int getSoTimtout() {
		return soTimtout;
	}

	public void setSoTimtout(int soTimtout) {
		this.soTimtout = soTimtout;
	}

	public int getMaxAttempts() {
		return maxAttempts;
	}

	public void setMaxAttempts(int maxAttempts) {
		this.maxAttempts = maxAttempts;
	}

	public String getClusterNodes() {
		return clusterNodes;
	}

	public void setClusterNodes(String clusterNodes) {
		this.clusterNodes = clusterNodes;
	}

}
