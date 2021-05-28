package com.webtech.database.repository;

import java.sql.ResultSet;
import java.util.List;

import com.webtech.database.model.Topic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

  @Query(value ="SELECT * FROM Topic t INNER JOIN Users u on u.users_id = t.users_id WHERE u.users_id = :users_id ORDER BY t.topic_id ASC", nativeQuery = true)
  List<Topic> findAllTopicsByUserId(@Param("users_id")String userid);

  @Query(value ="select count(i.card_id) as IndexCardCount, t.topic_id, t.des from topic t inner join users u on u.users_id = t.users_id left join indexcard i on i.topic_id = t.topic_id where t.users_id = :users_id group by t.topic_id, t.des order by t.topic_id", nativeQuery = true)
  List<GroupedTopics> findAllTopics(@Param("users_id")String userid);

}
