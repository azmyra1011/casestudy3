package com.tcs.CaseStudy3.controller;

import com.tcs.CaseStudy3.model.Event;
import com.tcs.CaseStudy3.model.User;
import com.tcs.CaseStudy3.repository.EventRepository;
import com.tcs.CaseStudy3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;


@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;


    //Display customer index page
    @GetMapping
    public String showCustomerForm(@RequestParam String userId, Model model){
        model.addAttribute("eventTypes", eventRepository.findAll());
        model.addAttribute("userId", userId);
        return "views/customers";
    }

    //Find Questions by Event
    @GetMapping("/questions")
    @ResponseBody
    public List<Event.CustomQuestion> getQuestions(@RequestParam String type){
        Event event = eventRepository.findByType(type);
        return event != null ? event.getCustom_questions() : new ArrayList<>();
    }


    // Create New Customer
    @PostMapping("/register")
    public String createCustomer(@RequestParam String username,
                                 @RequestParam String password,
                                 Model model) {

        // Check if username already exists
        if (userRepository.findByUsername(username) != null) {
            model.addAttribute("message", "Username already exists!");
            return "/views/register"; // Update with your actual register view path
        }

        // Check if password is alphanumeric (at least one letter and one digit)
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$";
        if (!password.matches(regex)) {
            model.addAttribute("message", "Password must be alphanumeric (letters and numbers only).");
            return "/views/register";
        }

        // Default role user as Customer
        String role = "customer";
        User user = new User();

        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        userRepository.save(user);

        return "redirect:/";
    }



}
