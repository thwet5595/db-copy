package com.thwet.dbcopy;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import com.thwet.dbcopy.model.User;
import com.thwet.dbcopy.rowmapper.UserRowMapper;

@SpringBootApplication
public class DbcopyApplication implements CommandLineRunner {

	@Autowired
	@Qualifier(value = "firstJdbcTemplate")
	public JdbcTemplate firstJdbcTemplate;
	
	@Autowired
	@Qualifier(value = "secondJdbcTemplate")
	public JdbcTemplate secondJdbcTemplate;

	@Autowired
	@Qualifier(value = "firstDataSource")
	public DataSource firstDataSource;

	public static void main(String[] args) {
		SpringApplication.run(DbcopyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		copyUsers();
	}

	public void copyUsers() {
		System.out.println("Inside Copy Users...");
		String selectUserQuery = "select * from users";
		String insertUserQuery = "insert into users (id, name, password, address ) values (?, ?, ?, ?)";
		List<User> users = new ArrayList<User>();
		users = firstJdbcTemplate.query(selectUserQuery, new UserRowMapper());
		System.out.println("Users...."+users.size());
		for (User user : users) {
			try {
				secondJdbcTemplate.update(insertUserQuery,
						new Object[] { user.getId(), user.getName(), user.getPassword(), user.getAddress() });
				
			} catch (Exception ex) {

			}
		}
	}
}
