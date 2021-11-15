package com.tube.clone.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class MariaDBDataSourceConfig {
	
	@Bean(name = "mariadbDataSourceProperties")
	@ConfigurationProperties("spring.datasource.mariadb")
	public DataSourceProperties mariadbDataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Bean(name = "mariadbDataSource")
	@ConfigurationProperties("spring.datasource.mariadb.configuration")
	public DataSource mariadbDataSource() {
		DataSourceProperties properties = mariadbDataSourceProperties();
		return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}
}
