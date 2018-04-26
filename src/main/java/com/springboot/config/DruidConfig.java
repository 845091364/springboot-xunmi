package com.springboot.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.springboot.properties.DruidProperties;

/**
 * @author liuyi
 *
 * @Date 2018年1月30日
 */
@Configuration
public class DruidConfig {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private DruidProperties druidProperties;

	@Bean
	public DataSource dataSource() {
		DruidDataSource datasource = new DruidDataSource();
		datasource.setUrl(druidProperties.getUrl());
		datasource.setUsername(druidProperties.getUsername());
		datasource.setPassword(druidProperties.getPassword());
		datasource.setDriverClassName(druidProperties.getDriverClassName());
		datasource.setInitialSize(druidProperties.getInitialSize());
		datasource.setMinIdle(druidProperties.getMinIdle());
		datasource.setMaxActive(druidProperties.getMaxActive());
		datasource.setMaxWait(druidProperties.getMaxWait());
		datasource.setTimeBetweenEvictionRunsMillis(druidProperties.getTimeBetweenEvictionRunsMillis());
		datasource.setMinEvictableIdleTimeMillis(druidProperties.getMinEvictableIdleTimeMillis());
		datasource.setValidationQuery(druidProperties.getValidationQuery());
		datasource.setTestWhileIdle(druidProperties.isTestWhileIdle());
		datasource.setTestOnBorrow(druidProperties.isTestOnBorrow());
		datasource.setTestOnReturn(druidProperties.isTestOnReturn());
		datasource.setPoolPreparedStatements(druidProperties.isPoolPreparedStatements());
		datasource.setMaxPoolPreparedStatementPerConnectionSize(druidProperties.getMaxPoolPreparedStatementPerConnectionSize());
		try {
			datasource.setFilters(druidProperties.getFilters());
		} catch (SQLException e) {
			logger.error("druid configuration initialization filter", e);
		}
		datasource.setConnectionProperties(druidProperties.getConnectionProperties());

		return datasource;
	}
}
