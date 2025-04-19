package com.example.users.service;



import com.example.users.model.User;
import com.example.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String registerUser(User user){
        if (userRepository.getUserByEmailHelper(user.getEmail()) == null){
        return    userRepository.registerUser(user);
        }return "The email: " + user.getEmail() + " is exists";
    }
    public String update(User user){
        if (userRepository.getUserById(user.getId()) != null) {

            return userRepository.update(user);
        }return "The user id : " + user.getId() + "is not exists";

    }
    public User getUserById(int id){
        return userRepository.getUserById(id);

    }

    public List<User> getAllUsers(){
        return userRepository.allUsers();
    }

    public String deleteById(int id){
        if (userRepository.getUserById(id) != null) {
            return userRepository.deleteById(id);
        }return "The user withe id: " +id+ " is not exists";
    }


}
