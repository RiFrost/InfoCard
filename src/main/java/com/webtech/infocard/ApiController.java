package com.webtech.infocard;

import com.webtech.service.TopicService;
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

    @GetMapping("/topic/{userId}")
    public Map<Long, String> getTopicListFromUser(@PathVariable(name = "userId") Long userId) {
        return topicService.getAllTopicsFromUser(userId);
    }

}
