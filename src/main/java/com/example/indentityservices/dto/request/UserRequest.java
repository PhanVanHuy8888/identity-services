package com.example.indentityservices.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UserRequest {
    @NotBlank(message = "")
    private String firstname;
    @NotBlank
    private String lastname;
    @Email
    private String email;

    private String password;
    @NotBlank
    private String address;
    @NotBlank
    private String username;
}
