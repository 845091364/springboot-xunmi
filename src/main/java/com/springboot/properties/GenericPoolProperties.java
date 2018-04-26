package com.springboot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author liuyi
 *
 * @Date 2018年1月30日
 */
@ConfigurationProperties(prefix = "redis.genericpool")
public class GenericPoolProperties {

	private int maxIdle;
	private int maxWaitMillis;
	private int minIdle;

	public int getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public int getMaxWaitMillis() {
		return maxWaitMillis;
	}

	public void setMaxWaitMillis(int maxWaitMillis) {
		this.maxWaitMillis = maxWaitMillis;
	}

	public int getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}

}
