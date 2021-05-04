package com.webtech.repository;

import com.webtech.model.IndexCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndexCardRepository extends JpaRepository<IndexCard, Long> {
}
