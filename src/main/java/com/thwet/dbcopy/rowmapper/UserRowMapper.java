package com.thwet.dbcopy.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.thwet.dbcopy.model.User;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {

		User user = new User();
		user.setId(resultSet.getInt("id"));
		user.setName(resultSet.getString("name"));
		user.setPassword(resultSet.getString("password"));
		user.setAddress(resultSet.getString("address"));
		return user;
	}
}
