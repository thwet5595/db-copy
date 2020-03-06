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
public class FirstDBConfiguration {

	@Value("${firstDb.datasource.driver-class-name}")
	private String driverClassName;

	@Value("${firstDb.datasource.username}")
	private String user;

	@Value("${firstDb.datasource.password}")
	private String password;

	@Value("${firstDb.datasource.url}")
	private String url;

	@Bean(name = "firstDataSource")
	public DataSource firstDataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUsername(user);
		dataSource.setPassword(password);
		dataSource.setJdbcUrl(url);
		dataSource.setMaximumPoolSize(30);
		dataSource.setMinimumIdle(30);
		return dataSource;
	}

	@Bean(name = "firstJdbcTemplate")
	public JdbcTemplate firstJdbcTemplate() {
		return new JdbcTemplate(firstDataSource());
	}

	/*
	 * @Bean(name = "firstCompanyRepository") public CompanyRepository
	 * kyuyoCompanyRepository() { return new
	 * CompanyRepository(kyuyoJdbcTemplate()); }
	 */
}
