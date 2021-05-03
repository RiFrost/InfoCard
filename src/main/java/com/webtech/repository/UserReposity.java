package com.webtech.repository;

import com.webtech.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReposity extends JpaRepository<User, Long> {
}
