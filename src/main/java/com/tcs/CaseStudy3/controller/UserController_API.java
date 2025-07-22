package com.tcs.CaseStudy3.controller;


import com.tcs.CaseStudy3.model.User;
import com.tcs.CaseStudy3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/users")
public class UserController_API {

    @Autowired
    private UserRepository userRepository;

    //Display All Users
    @GetMapping
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }
}
