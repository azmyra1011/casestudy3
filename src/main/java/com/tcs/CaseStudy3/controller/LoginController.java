package com.tcs.CaseStudy3.controller;

import com.tcs.CaseStudy3.model.User;
import com.tcs.CaseStudy3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String showLoginForm() {
        return "views/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        User user = userRepository.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            if ("admin".equalsIgnoreCase(user.getRole())) {
                return "redirect:/events";
            } else if ("customer".equalsIgnoreCase(user.getRole())) {
                return "redirect:/customers?userId=" + user.getId(); // Pass userId in URL
            } else {
                model.addAttribute("error", "Invalid username or password");
                return "views/login";
            }
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "views/login";
        }
    }

    @GetMapping("/register")
    public String showRegisterForm(){
        return "views/createUserForm";
    }

}


