package com.example.indentityservices.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
public class UserUpdateRequest {
    private Long id;

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String address;
    private String username;

}
