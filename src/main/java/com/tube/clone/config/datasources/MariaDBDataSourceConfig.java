package com.tube.clone.config.datasources;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
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
	
	/**
	 * MyBatis Session Factory
	 * */
	@Bean(name = "mariadbSqlSessionFactory")
	public SqlSessionFactory mariadbSqlSessionFactory(@Qualifier("mariadbDataSource") DataSource mariadbDataSource, ApplicationContext applicationContext) throws Throwable {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(mariadbDataSource);
		sessionFactory.setMapperLocations(applicationContext.getResources("classpath:mappers/mariadb/**/*.xml"));
		
		return sessionFactory.getObject();
	}
	
	/**
	 * MyBatis Session Template
	 * */
	@Bean(name = "mariadbSqlSessionTemplate")
	public SqlSessionTemplate mariadbSqlSessionTemplate(@Qualifier("mariadbSqlSessionFactory") SqlSessionFactory sessionFacotry) {
		return new SqlSessionTemplate(sessionFacotry);
	}
	
}
