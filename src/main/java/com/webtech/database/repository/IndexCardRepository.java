package com.webtech.database.repository;

import java.util.List;

import com.webtech.database.model.IndexCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IndexCardRepository extends JpaRepository<IndexCard, Long> {

  @Query(value ="SELECT * FROM Indexcard i INNER JOIN Topic t on i.topic_id = t.topic_id WHERE t.topic_id = :topic_id ORDER BY i.card_id ASC", nativeQuery = true)
  List<IndexCard> findAllIndexCardsByTopicId(@Param("topic_id")Long topicId);

  void deleteById(Long id);
}
