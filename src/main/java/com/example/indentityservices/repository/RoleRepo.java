package com.example.indentityservices.repository;

import com.example.indentityservices.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Integer> {
}
