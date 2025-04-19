package com.example.users.repository;


import com.example.users.model.User;
import com.example.users.repository.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
@Autowired
    private JdbcTemplate jdbcTemplate;

public String registerUser(User user){

        String sql = "INSERT INTO users (first_name,last_name,email,age,address,joining_date)  VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(sql,user.getFirstName(),user.getLastName(),user.getEmail(),user.getAge(),user.getAddress(),user.getJoiningDate());

        return "The user: " + user.getFirstName() +  " is register successfully";



}
public String update(User user){
        String sql = "UPDATE users SET first_name = ? ,last_name = ? ,email = ?,age = ? ,address = ? ,joining_date = ?)  WHERE id =?  ";
        jdbcTemplate.update(sql,user.getFirstName(),user.getLastName(),user.getEmail(),user.getAge(),user.getAddress(),user.getJoiningDate(),user.getId());
        return "The user: " + user.getFirstName() + " is update";

}


public User getUserById(int id){
    try {
        String sql = "SELECT * FROM users WHERE id = ?";
        return  jdbcTemplate.queryForObject(sql,new UserMapper(),id);
    } catch (Exception e) {
        System.out.println(e.getMessage());
        return null;
    }
}
public User getUserByEmailHelper(String email){
    try {
        String sql = "SELECT * FROM users WHERE email = ?";
        return jdbcTemplate.queryForObject(sql,new UserMapper(),email);
    } catch (Exception e) {
        System.out.println(e.getMessage());
        return null;
    }
}

    public List<User> allUsers(){

        String sql ="SELECT * FROM users";
      return jdbcTemplate.query(sql,new UserMapper());


    }
    public String deleteById(int id){
        String sql = "DELETE FROM users WHERE id= ?";
        jdbcTemplate.update(sql,id);
        return "You user is deleted";

    }
}
