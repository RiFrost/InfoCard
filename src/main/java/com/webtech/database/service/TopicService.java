package com.webtech.database.service;

import com.webtech.database.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class TopicService {

    @Autowired
    private UserService userService;

    public Map<Long, String> getAllTopicsFromUser(String userId) {
        Map<Long, String> topicMap = new HashMap<>();
        Set<Topic> topicSet = userService.findById(userId).getTopics();
        for (Topic t : topicSet) {
            topicMap.put(t.getId(), t.getTopicName());
        }
        return topicMap;
    }

}
