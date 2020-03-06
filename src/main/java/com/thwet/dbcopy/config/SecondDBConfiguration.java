package com.thwet.dbcopy.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:config.properties")
public class SecondDBConfiguration {

	@Value("${secondDb.datasource.driver-class-name}")
	private String driverClassName;

	@Value("${secondDb.datasource.username}")
	private String user;

	@Value("${secondDb.datasource.password}")
	private String password;

	@Value("${secondDb.datasource.url}")
	private String url;

	@Bean(name = "secondDataSource")
	public DataSource secondDataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUsername(user);
		dataSource.setPassword(password);
		dataSource.setJdbcUrl(url);
		dataSource.setMaximumPoolSize(30);
		dataSource.setMinimumIdle(30);
		return dataSource;
	}

	@Bean(name = "secondJdbcTemplate")
	public JdbcTemplate firstJdbcTemplate() {
		return new JdbcTemplate(secondDataSource());
	}
}
