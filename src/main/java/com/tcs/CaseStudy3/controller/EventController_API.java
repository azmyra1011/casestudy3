package com.tcs.CaseStudy3.controller;


import com.tcs.CaseStudy3.model.Event;
import com.tcs.CaseStudy3.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "*") // Allow frontend to fetch from any origin
public class EventController_API {

    @Autowired
    private EventRepository eventRepository;

    //Display All Events & Questions
    @GetMapping
    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    //Add New Event
    @PostMapping
    public ResponseEntity<String> createEvent(@RequestBody Event event) {
        eventRepository.save(event);
        return ResponseEntity.ok("/events"); // frontend should redirect here
    }

}
