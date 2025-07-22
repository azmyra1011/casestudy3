package com.tcs.CaseStudy3.controller;

import com.tcs.CaseStudy3.model.Event;
import com.tcs.CaseStudy3.model.EventRegister;
import com.tcs.CaseStudy3.repository.EventRegisterRepository;
import com.tcs.CaseStudy3.repository.EventRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventRegisterRepository eventRegisterRepository;

    //Display Event index page
    @GetMapping("/events")
    public String showEvents(Model model) {
        List<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);
        return "/views/admin/ViewEvent";
    }

    //Display Customer's Event History from CustID
    @GetMapping("/customer/history")
    public String viewHistory(@RequestParam String custId, Model model) {
        List<EventRegister> events = eventRegisterRepository.findByCustId(custId);
        model.addAttribute("events", events);
        model.addAttribute("userId", custId);
        return "/views/success";
    }


    //Register New Event
    @PostMapping("/submit-event")
    public String submitEvent(HttpServletRequest request, Model model) {
        String eventType = request.getParameter("eventType");
        String userId = request.getParameter("userId"); // Assume coming from form or session

        Map<String, String[]> paramMap = request.getParameterMap();
        Map<String, Object> answers = new HashMap<>();

        for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
            String key = entry.getKey();
            if (!key.equals("eventType") && !key.equals("userId")) {
                String value = entry.getValue()[0];

                // 🔧 Replace dots in keys to prevent MongoDB error
                String safeKey = key.replace(".", "_");

                if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
                    answers.put(safeKey, Boolean.valueOf(value));
                } else {
                    try {
                        answers.put(safeKey, Integer.valueOf(value));
                    } catch (NumberFormatException e) {
                        answers.put(safeKey, value);
                    }
                }
            }
        }

        EventRegister event = new EventRegister();
        event.setCustId(userId);
        event.setEventType(eventType);
        event.setAnswers(answers);
        eventRegisterRepository.save(event);

        model.addAttribute("event", event);
        return "redirect:/customer/history?custId=" + userId;
    }

    @GetMapping("/show/event")
    public String showAllEvents(Model model){
        List<EventRegister> eventRegisters = eventRegisterRepository.findAll();
        model.addAttribute("customers", eventRegisters);
        return "views/admin/ViewCustomerEvent";
    }

}
