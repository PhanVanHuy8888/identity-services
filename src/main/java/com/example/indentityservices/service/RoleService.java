package com.example.indentityservices.service;

import com.example.indentityservices.dto.request.RoleRequest;
import com.example.indentityservices.model.Role;
import com.example.indentityservices.repository.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepo roleRepo;


    public Role createRole(RoleRequest request) {
        Role role = new Role();
        role.setName(request.getName());
        role.setDescription(request.getDescription());
        roleRepo.save(role);
        return role;
    }
}
