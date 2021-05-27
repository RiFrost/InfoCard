package com.webtech.database.repository;

import java.util.List;

import com.webtech.database.model.Topic;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

  @Query(value ="SELECT * FROM Topic t INNER JOIN Users u on u.users_id = t.users_id WHERE u.users_id = :users_id ORDER BY t.topic_id ASC", nativeQuery = true)
  List<Topic> findAllTopicsByUserId(@Param("users_id")String userid);

}
