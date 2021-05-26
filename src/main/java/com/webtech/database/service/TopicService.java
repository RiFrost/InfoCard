package com.webtech.database.service;

import com.webtech.database.model.Topic;
import com.webtech.database.repository.TopicRepository;
import com.webtech.database.repository.UserRepository;
import com.webtech.exceptions.NotFoundException;
import com.webtech.infocard.TopicRequest;
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

    public Topic findTopicById(Long topicId) {
        return topicRepo.findById(topicId).orElseThrow(() -> new NotFoundException("TopicId " + topicId + " does not exist!"));
    }

    public TopicResponse addTopic(String userId, TopicRequest topicRequest) {
        Topic newTopic = new Topic(userService.findById(userId), topicRequest.getTopicName());
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
    
    public void deleteTopics(List<TopicRequest> requestTopicList) {
        for (TopicRequest topicR : requestTopicList) {
            if (topicRepo.existsById(topicR.getId())) {
                topicRepo.delete(findTopicById(topicR.getId()));
            }
        }
    }

    public TopicRequest renameTopic(TopicRequest topicRequest) {
        Topic topic = findTopicById(topicRequest.getId());
        topic.setTopicName(topicRequest.getTopicName());
        topicRepo.save(topic);
        return (TopicRequest) topicRequest;
    }

}
