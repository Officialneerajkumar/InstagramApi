package com.example.Instagram.API.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private String password;
    private String phoneNumber;

    public User(String firstName,String lastName,Integer age,String email,String password,String phoneNumber){
        this.firstName=firstName;
        this.lastName=lastName;
        this.age=age;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.password=password;
    }
}
