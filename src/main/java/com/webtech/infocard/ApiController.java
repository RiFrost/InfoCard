package com.webtech.infocard;

import com.webtech.model.Topic;
import com.webtech.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private TopicRepository topicRepository;

    @GetMapping("/test")
    public String home() {
        return "InfoCard - Your new Way of learning!";
    }

    // @GetMapping("/db")
    // public String db() {
    //   boolean checkDbConnection = DbConnection.getInstance().checkDbConnection();
    //   return checkDbConnection ? "DB Verbindung erfolgreich" : "DB Verbindung fehlgeschlagen";
    // }

    @GetMapping("/topicList")
    public List<Topic> getTopicList() {
        List<Topic> topicList = topicRepository.findAll();
        return topicList;
    }

}
