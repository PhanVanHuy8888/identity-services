package com.example.indentityservices.controller;

import com.example.indentityservices.dto.request.RoleRequest;
import com.example.indentityservices.model.Role;
import com.example.indentityservices.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class RoleController {
    private final RoleService roleService;

    @PostMapping("/createRole")
    public Role createRole(@RequestBody RoleRequest request) {
        return roleService.createRole(request);
    }
}
