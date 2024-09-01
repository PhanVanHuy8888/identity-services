package com.example.indentityservices.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class UserResponse {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String address;
}
