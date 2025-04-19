package com.example.users.controller;



import com.example.users.model.User;
import com.example.users.service.PollClientService;
import com.example.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PollClientService pollClientService;
    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            String result = userService.registerUser(user);
if (result.contains(" successfully")) {
    return new ResponseEntity<>(result, HttpStatus.OK);
}return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping
    public ResponseEntity<String> update(@RequestBody User user){
        try {

            return  new ResponseEntity<>(userService.update(user), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity<User> getUserById(@RequestParam int id){
        try {
        User user = userService.getUserById(id);
        if (user != null) {
        return new ResponseEntity<>(user, HttpStatus.OK);
        }else return new ResponseEntity("The user by id: " + id + " is not exists",HttpStatus.NOT_FOUND );
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUser(){
        try {

            return  new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/delete/")
    public ResponseEntity<String> deleteById(@RequestParam int id){
        try {
            String result = userService.deleteById(id);
            if (result.contains("deleted")){
            pollClientService.deleteAnswer(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
