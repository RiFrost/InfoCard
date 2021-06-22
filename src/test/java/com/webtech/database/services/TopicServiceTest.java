package com.webtech.database.services;

import com.webtech.database.model.Topic;
import com.webtech.database.model.User;
import com.webtech.database.repository.TopicRepository;
import com.webtech.database.service.TopicService;
import com.webtech.database.service.UserService;
import com.webtech.exceptions.NotFoundException;
import com.webtech.infocard.responsemodel.TopicResponse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
public class TopicServiceTest {

    @Mock
    private TopicRepository repository;

    @Mock
    private UserService userService;

    @Mock
    private Topic mockTopic;

    @InjectMocks
    private TopicService underTest;


    @Test
    @DisplayName("should find topic by topicid")
    void testFindTopicByTopicId() {
        //given
        var user = new User("Max", "Mustermann", "testmail@web.de", "123456");
        var topic = new Topic(user, "Webtech");
        topic.setId(1L);
        doReturn(Optional.of(topic)).when(repository).findById(1l);

        // when
        var actual = underTest.findTopicById(1l);

        //then
        verify(repository).findById(1l);
        Assertions.assertSame(actual.getTopicName(), topic.getTopicName(), "The name of topic was wrong");
    }

    @Test
    @DisplayName("should thrown NotFoundException when topic was not found by id")
    public void testExceptionWhenTopicIsNotAvailable() {
        //given
        long topicId = 1l;
        doReturn(Optional.empty()).when(repository).findById(topicId);

        //when
                NotFoundException thrown = assertThrows(
                NotFoundException.class,
                () -> underTest.findTopicById(topicId));

        //then
        verify(repository).findById(topicId);
        assertEquals("TopicId " + topicId + " does not exist!", thrown.getMessage());
    }

    @Test
    @DisplayName("should add a new topic to database")
    void testAddTopicToDatabase() {
        //given
        var user = new User("Max", "Mustermann", "testmail@web.de", "123456");
        var userId = "abc";
        user.setId("abc");
        var newTopicName = "Webtech";
        TopicResponse topicResponse = new TopicResponse(1L, newTopicName, 0);
        Topic topic = new Topic(user, newTopicName);
        topic.setId(1L);
        doReturn(user).when(userService).findById(userId);
        doReturn(topic).when(repository).save(any(Topic.class));

        //when
        TopicResponse actual = underTest.addTopic(userId, topicResponse);

        //then
        verify(repository).save(any(Topic.class));
        Assertions.assertSame(actual.getTopicName(), newTopicName, "The name of topic was wrong");
        Assertions.assertSame(actual.getId(), topic.getId(), "Id of topic response was wrong");
        Assertions.assertSame(actual.getIndexCardCount(), 0, "Index card count was different");
    }

   @Test
   @DisplayName("should return a list of topics belonging to userId")
   void testAllGettingTopicsFromUser() {
      //given
       var user = new User("Max", "Mustermann", "testmail@web.de", "123456");
       var userId = "abc";
       user.setId("abc");
       List<GroupedTopicsImplTest> groupedTopics = List.of(
           new GroupedTopicsImplTest(1L, "Webtech", 2),
           new GroupedTopicsImplTest(2L, "DBTech", 0));
       doReturn(user).when(userService).findById(userId);
       doReturn(groupedTopics).when(repository).findAllTopics(userId);
       List<TopicResponse> expected = List.of(
           new TopicResponse(1L, "Webtech", 2),
           new TopicResponse(2L, "DBTech", 0)
       );

      //when
      List<TopicResponse> actual = underTest.getAllTopicsFromUser(userId);

      //then
      verify(repository).findAllTopics(any());
      Assertions.assertSame(actual.size(), 2, "Liste hat nicht die gleiche Länge");
      assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
   }
   

}




