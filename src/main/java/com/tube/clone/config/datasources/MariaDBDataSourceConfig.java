package com.tube.clone.config.datasources;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.tube.clone.config.datasources", entityManagerFactoryRef = "mariadbEntityManagerFactory", transactionManagerRef = "mariadbTransactionManager")
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
	
	/**
	 * JPA ¿¬µ¿
	 * */
	@Bean(name = "mariadbEntityManagerFactory")
	public EntityManagerFactory entityManagerFactroy(@Qualifier("mariadbDataSource") DataSource dataSource) {
		//Create properties
		final Map<String, Object> properties = new HashMap<>();
		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		final HibernateJpaVendorAdapter jpaAdapter = new HibernateJpaVendorAdapter();
		
		//Set Config
		properties.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
		properties.put("hibernate.implicit_naming_stratrgy", SpringImplicitNamingStrategy.class.getName());
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		
		em.setDataSource(dataSource);
		em.setPackagesToScan("com.tube.clone.config.datasources");
		em.setJpaVendorAdapter(jpaAdapter);
		em.setJpaPropertyMap(properties);
		em.afterPropertiesSet();
		
		return em.getObject();
	}
	
	/**
	 * Transaction Manager
	 * */
	@Bean(name = "mariadbTransactionManager")
	public PlatformTransactionManager transactionManager(@Qualifier("mariadbEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
}
