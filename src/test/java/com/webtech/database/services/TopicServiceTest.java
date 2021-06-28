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
    private TopicRepository topicRepo;

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
        var user = TestModelFactory.createUser();
        var expected = TestModelFactory.createTopic(user);
        doReturn(Optional.of(expected)).when(topicRepo).findById(expected.getId());

        // when
        var actual = underTest.findTopicById(expected.getId());

        //then
        verify(topicRepo).findById(expected.getId());
        Assertions.assertSame(actual.getTopicName(), expected.getTopicName(), "Topicnames are different");
        Assertions.assertSame(actual.getId(), expected.getId(), "Ids are different");
        Assertions.assertSame(actual.getUser(), expected.getUser(), "User are different");
    }

    @Test
    @DisplayName("should thrown NotFoundException when topic was not found by id")
    public void testExceptionWhenTopicIsNotAvailable() {
        //given
        doReturn(Optional.empty()).when(topicRepo).findById(1L);

        //when
        NotFoundException thrown = assertThrows(
            NotFoundException.class,
            () -> underTest.findTopicById(1L));

        //then
        verify(topicRepo).findById(1L);
        assertEquals("TopicId " + 1L + " does not exist!", thrown.getMessage());
    }

    @Test
    @DisplayName("should add a new topic to database")
    void testAddTopicToDatabase() {
        //given
        var user = TestModelFactory.createUser();
        var topicResponse = TestModelFactory.createTopicResWoCardCount();
        var topic = TestModelFactory.createTopic(user);
        doReturn(user).when(userService).findById(user.getId());
        doReturn(topic).when(topicRepo).save(any(Topic.class));

        //when
       var actual = underTest.addTopic(user.getId(), topicResponse);

        //then
        verify(topicRepo).save(any(Topic.class));
        Assertions.assertSame(actual.getTopicName(), topic.getTopicName(), "The name of topic was wrong");
        Assertions.assertSame(actual.getId(), topic.getId(), "Id of topic response was wrong");
        Assertions.assertSame(actual.getIndexCardCount(), 0, "Index card count was different");
    }

   @Test
   @DisplayName("should return a list of topics belonging to userId")
   void testAllGettingTopicsFromUser() {
      //given
       var user = TestModelFactory.createUser();
       var groupedTopics = TestModelFactory.createGroupedTopics();
       var expected = TestModelFactory.createTopicResList();
       doReturn(user).when(userService).findById(user.getId());
       doReturn(groupedTopics).when(topicRepo).findAllTopics(user.getId());
       
      //when
      var actual = underTest.getAllTopicsFromUser(user.getId());

      //then
      verify(topicRepo).findAllTopics(user.getId());
      Assertions.assertSame(actual.size(), 2, "Liste hat nicht die gleiche Länge");
      assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
   }
   

   @Test
   @DisplayName("should delete topics by given ids")
   void testDeletingListOfTopicsById() {
        //given
        List<Long> ids = List.of(1L, 2L);

        //when
        underTest.deleteTopics(ids);

        //then
        verify(topicRepo).existsById(ids.get(0));
        verify(topicRepo).existsById(ids.get(1));
   }

    @Test
    @DisplayName("should return renamed topicResponse")
    void testRenamingOfTopic() {
    //given
    var expected = TestModelFactory.createTopicRes(1L, "Datenbanken", 0);
    var user = TestModelFactory.createUser();
    var topic = TestModelFactory.createTopic(user);
    doReturn(Optional.of(topic)).when(topicRepo).findById(topic.getId());

    //when
    var actual = underTest.renameTopic(expected);

    //then
    verify(topicRepo).save(topic);
    Assertions.assertSame(actual.getTopicName(), expected.getTopicName(), "The name of topic was wrong");
    Assertions.assertSame(actual.getId(), expected.getId(), "Id of topic response was wrong");
    Assertions.assertSame(actual.getIndexCardCount(), expected.getIndexCardCount(), "Index card count was different");
}


}




