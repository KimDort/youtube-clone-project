package com.tube.clone.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class PostgresqlDataSourceConfig {
	
	@Bean(name = "postgresqlDataSourceProperties")
	@ConfigurationProperties("spring.datasource.postgresql")
	public DataSourceProperties postgresqlDataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Bean(name = "postgresqlDataSource")
	@ConfigurationProperties("spring.datasource.postgresql.configuration")
	public DataSource postgresqlDataSource() {
		DataSourceProperties properties = postgresqlDataSourceProperties();
		return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}
}
