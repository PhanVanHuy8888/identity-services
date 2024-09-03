package com.example.indentityservices.repository;

import com.example.indentityservices.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepo extends JpaRepository<Permission, Integer> {
}
