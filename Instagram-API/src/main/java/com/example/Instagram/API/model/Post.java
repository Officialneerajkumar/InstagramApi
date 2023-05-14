package com.example.Instagram.API.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int postId;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    private String postData; //(use any dummy string here ex "abcd" . this will be replaced by image link string in class)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
