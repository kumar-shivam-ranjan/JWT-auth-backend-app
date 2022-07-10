package com.jwt.demo.repository;

import com.jwt.demo.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer > {
}
