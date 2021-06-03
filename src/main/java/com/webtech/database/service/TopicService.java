package com.webtech.database.service;

import com.webtech.database.model.Topic;
import com.webtech.database.repository.GroupedTopics;
import com.webtech.database.repository.TopicRepository;
import com.webtech.database.repository.UserRepository;
import com.webtech.exceptions.NotFoundException;
import com.webtech.infocard.TopicResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public TopicResponse addTopic(String userId, TopicResponse topicResponse) {
        Topic newTopic = new Topic(userService.findById(userId), topicResponse.getTopicName());
        topicRepo.save(newTopic);
        return new TopicResponse(newTopic.getId(), newTopic.getTopicName(), 0);
    }

    public List<TopicResponse> getAllTopicsFromUser(String userId) {
        List<GroupedTopics> topicList = topicRepo.findAllTopics(userService.findById(userId).getId());
        return topicList.stream()
                .map(t -> new TopicResponse(t.getTopic_id(), t.getDes(), t.getIndexCardCount()))
                .collect(Collectors.toList());
    }
    
    public void deleteTopics(List<Long> topicIdList) {
        for (Long topId : topicIdList) {
            if (topicRepo.existsById(topId)) {
                topicRepo.delete(findTopicById(topId));
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