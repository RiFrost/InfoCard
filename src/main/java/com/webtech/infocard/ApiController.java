package com.webtech.infocard;

import com.webtech.databaseConnection.DbConnection;
import com.webtech.databaseConnection.Topic;
import com.webtech.databaseConnection.TopicRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



import org.springframework.web.bind.annotation.RequestMapping;



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
  public List<Topic> getTopicList(){
    List<Topic> topicList = topicRepository.findAll();
    return topicList;
  }

}
