package com.webtech.database.repository;

import com.webtech.database.model.EnumRole;
import com.webtech.database.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(EnumRole name);
}
