package com.webtech.repository;

import java.util.Optional;

import com.webtech.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

  Optional<User> findByEmail(String email);

  Boolean existsByEmail(String email);

}
