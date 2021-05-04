package com.webtech.infocard;

import com.webtech.service.TopicService;
import com.webtech.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private TopicService topicService;

    @GetMapping("/test")
    public String home() {
        return "InfoCard - Your new Way of learning!";
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        System.out.println("Email: " + user.getEmail());
        System.out.println("Password: " + user.getPassword());
        return new User("Richard", "Frost", user.getEmail());
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        System.out.println("Firstname: " + user.getFirstName());
        System.out.println("Lastname: " + user.getLastName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Password: " + user.getPassword());
        return new User(user.getFirstName(), user.getLastName(), user.getEmail());
    }

    @GetMapping("/topic/{userId}")
    public Map<Long, String> getTopicListFromUser(@PathVariable(name = "userId") Long userId) {
        return topicService.getAllTopicsFromUser(userId);
    }

}
