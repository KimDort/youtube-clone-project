package com.tube.clone.config.datasources;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(
		basePackages = "com.tube.clone"
		, entityManagerFactoryRef = "postgresqlEntityManagerFactory"
		, transactionManagerRef = "postgresqlTransactionManager")
@EntityScan(basePackages = "com.tube.clone.config.message.domain")
public class PostgresqlDataSourceConfig {
	
	@Autowired
	private Environment env;
	
	@Primary
	@Bean(name = "postgresqlDataSourceProperties")
	@ConfigurationProperties("spring.datasource.postgresql")
	public DataSourceProperties postgresqlDataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Primary
	@Bean(name = "postgresqlDataSource")
	@ConfigurationProperties("spring.datasource.postgresql.configuration")
	public DataSource postgresqlDataSource() {
		DataSourceProperties properties = postgresqlDataSourceProperties();
		return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}
	
	/**
	 * JPA ¿¬µ¿
	 * */
	@Primary
	@Bean(name = "postgresqlEntityManagerFactory")
	public EntityManagerFactory entityManagerFactroy(DataSource dataSource) {
		//Create properties
		final Map<String, Object> properties = new HashMap<>();
		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		final HibernateJpaVendorAdapter jpaAdapter = new HibernateJpaVendorAdapter();
		
		//Set Config
		properties.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
		properties.put("hibernate.implicit_naming_stratrgy", SpringImplicitNamingStrategy.class.getName());

		jpaAdapter.setGenerateDdl(Boolean.valueOf(env.getProperty("spring.jpa.show-sql")));
		jpaAdapter.setShowSql(Boolean.valueOf(env.getProperty("spring.jpa.generate-ddl")));
		
		em.setDataSource(dataSource);
		em.setPackagesToScan("com.tube.clone.config.message");
		em.setJpaVendorAdapter(jpaAdapter);
		em.setJpaPropertyMap(properties);
		em.afterPropertiesSet();
		
		return em.getObject();
	}
	
	/**
	 * Transaction Manager
	 * */
	@Primary
	@Bean(name = "postgresqlTransactionManager")
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
}
