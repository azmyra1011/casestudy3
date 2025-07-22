package com.tcs.CaseStudy3.controller;


import com.tcs.CaseStudy3.model.User;
import com.tcs.CaseStudy3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController_API {

    @Autowired
    private UserRepository userRepo;

    @GetMapping
    public String showLoginForm(){
        return "/views/login";

    }

    @PostMapping("/login")
    public String login(@RequestBody User username){
        User user = userRepo.findByUsername(username.getUsername());
        if (user != null && user.getPassword().equals(username.getPassword())){
            return "yey bole login";
        }
        else{
            return "haa haa salah";
        }
    }
}
