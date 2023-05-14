package com.example.Instagram.API.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpInput {
    private String firstname;
    private String lastName;
    private int age;
    private String email;
    private String password;
    private String phoneNumber;
}
