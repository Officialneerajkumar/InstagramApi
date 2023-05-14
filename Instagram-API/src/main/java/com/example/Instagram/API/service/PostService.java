package com.example.Instagram.API.service;

import com.example.Instagram.API.dao.PostRepo;
import com.example.Instagram.API.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private AuthenticationTokenService authService;
    public ResponseEntity<String> addPost(String givenToken, Post givenData) {
        // authentication
        HttpStatus status = null;
        String message = null;
        Post post = null;
        if(authService.authenticate(givenToken)){
            LocalDate createDate = LocalDate.now();
            post.setPostData(givenData.getPostData());
            post.setCreatedDate(createDate);
            post.getUser().setUserId(givenData.getUser().getUserId());
            status = HttpStatus.CREATED;
            message = "Post creaated successfully !!!";
        }else{
            status = HttpStatus.BAD_REQUEST;
            message = "Invalid User !!!";
        }
        return new ResponseEntity<>(message,status);
    }

    public ResponseEntity<Post> getPost(int postId, String givenToken) {
        HttpStatus status = null;
        Post post = null;
        if(authService.authenticate(givenToken)){
            post = postRepo.findById(postId).get();
            status = HttpStatus.FOUND;
        }else{
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(post,status);
    }
}
