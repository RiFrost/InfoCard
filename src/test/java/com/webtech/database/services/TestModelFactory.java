package com.webtech.database.services;

import java.util.List;

import com.webtech.database.model.Topic;
import com.webtech.database.model.User;
import com.webtech.infocard.responsemodel.TopicResponse;

public class TestModelFactory {

  private TestModelFactory() {}

  public static User createUser() {
    var testUser = new User("Maxim", "Musterfrau", "testmail@web.de", "123456");
    testUser.setId("dummyId");
    return testUser;
  }

  public static Topic createTopic(User user) {
    var testTopic = new Topic(user, "Webtech");
    testTopic.setId(1L);
    return testTopic;
  }

  public static Topic createTopic(Long id, User user, String topicName) {
    var testTopic = new Topic(user, topicName);
    testTopic.setId(id);
    return testTopic;
  }

  public static TopicResponse createTopicResWoCardCount() {
    var testTopicRes = new TopicResponse();
    testTopicRes.setId(1L);
    testTopicRes.setTopicName("Webtech");
    return testTopicRes;
  }

  public static TopicResponse createTopicRes() {
    var testTopicRes = new TopicResponse(1L, "Webtech", 0);
    return testTopicRes;
  }

  public static TopicResponse createTopicRes(Long id, String topicName, int indexCardCount) {
    var testTopicRes = new TopicResponse(id, topicName, indexCardCount);
    return testTopicRes;
  }

  public static List<TopicResponse> createTopicResList() {
    var topicResList = List.of(new TopicResponse(1L, "Webtech", 0),
                              new TopicResponse(2L, "Englisch", 2));
    return topicResList;
  }

  public static List<GroupedTopicsImplTest> createGroupedTopics() {
    var groupedTopics = List.of(new GroupedTopicsImplTest(1L, "Webtech", 0),
    new GroupedTopicsImplTest(2L, "Englisch", 2));
    return groupedTopics;
  }

  
}
