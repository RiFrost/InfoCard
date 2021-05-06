package com.webtech.repository;

import java.util.Optional;

import com.webtech.model.EnumRole;
import com.webtech.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(EnumRole name);
}
