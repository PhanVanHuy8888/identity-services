package com.example.indentityservices.controller;

import com.example.indentityservices.dto.request.UserRequest;
import com.example.indentityservices.dto.request.UserUpdateRequest;
import com.example.indentityservices.dto.response.ResponseData;
import com.example.indentityservices.dto.response.UserResponse;


import com.example.indentityservices.model.User;
import com.example.indentityservices.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createUser")
    public ResponseData<UserResponse> createUser(@RequestBody @Valid UserRequest request) {
        return new ResponseData<>(HttpStatus.CREATED.value(), "Create user successfully",
                userService.createUser(request));
    }

    @GetMapping("/getAll")
    public ResponseData<List<UserResponse>> getAll() {
        return ResponseData.<List<UserResponse>>builder()
                .status(HttpStatus.OK.value())
                .message("Get-All-User")
                .data(userService.getAll())
                .build();
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseData deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseData.builder()
                .status(HttpStatus.OK.value())
                .message("Deleted successfully User")
                .build();
    }

    @GetMapping("/getUserById/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseData<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest request) {
        return ResponseData.<UserResponse>builder()
                .status(HttpStatus.OK.value())
                .message("User updated successfully")
                .data(userService.updateUser(id, request))
                .build();
    }

}
