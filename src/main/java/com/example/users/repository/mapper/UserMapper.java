package com.example.users.repository.mapper;


import com.example.users.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
       User user = new User();
       user.setId(rs.getInt("id"));
       user.setFirstName(rs.getString("first_name"));
       user.setLastName(rs.getString("last_name"));
       user.setEmail(rs.getString("email"));
       user.setAddress(rs.getString("address"));
       user.setAge(rs.getInt("age"));
       user.setJoiningDate(rs.getDate("joining_date"));


        return user;
    }
}
