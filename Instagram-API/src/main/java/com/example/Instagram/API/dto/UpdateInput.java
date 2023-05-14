package com.example.Instagram.API.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInput {
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private String password;
    private String phoneNumber;
}
