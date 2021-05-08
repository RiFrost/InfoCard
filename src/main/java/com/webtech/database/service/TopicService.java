package com.webtech.database.service;

import com.webtech.database.model.Topic;
import com.webtech.database.repository.TopicRepository;
import com.webtech.database.repository.UserRepository;
import com.webtech.infocard.TopicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TopicService {

    @Autowired
    private UserService userService;

    @Autowired
    TopicRepository topicRepo;

    @Autowired
    UserRepository userRepo;

    public Topic findTopicById(long topicId) {
        return topicRepo.findById(topicId).orElseThrow(() -> new IllegalArgumentException("TopicId " + topicId + " does not exist!"));
    }

    public TopicResponse addTopic(String userId, TopicResponse topicResponse) {
        Topic newTopic = new Topic(userService.findById(userId), topicResponse.getTopicName());
        topicRepo.save(newTopic);
        return new TopicResponse(newTopic.getId(), newTopic.getTopicName());
    }

    public List<TopicResponse> getAllTopicsFromUser(String userId) {
        List<TopicResponse> topicList = new ArrayList<>();
        Set<Topic> topicSet = userService.findById(userId).getTopics();
        for (Topic t : topicSet) {
            topicList.add(new TopicResponse(t.getId(), t.getTopicName()));
        }
        return topicList;
    }

    public void deleteTopics(List<TopicResponse> topicResponseList) {
        for (TopicResponse topicR : topicResponseList) {
            if (topicRepo.existsById(topicR.getId())) {
                topicRepo.delete(findTopicById(topicR.getId()));
            }
        }
    }

    public TopicResponse renameTopic(TopicResponse topicResponse) {
        Topic topic = findTopicById(topicResponse.getId());
        topic.setTopicName(topicResponse.getTopicName());
        topicRepo.save(topic);
        return topicResponse;
    }

}
