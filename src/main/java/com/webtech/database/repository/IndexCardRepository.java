package com.webtech.database.repository;

import com.webtech.database.model.IndexCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndexCardRepository extends JpaRepository<IndexCard, Long> {
}
