package com.example.Instagram.API.controller;

import com.example.Instagram.API.model.Post;
import com.example.Instagram.API.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/create-post")
    public ResponseEntity<String> createPost(@RequestBody Post post, @RequestParam String givenToken){
        return postService.addPost(givenToken,post);
    }
    @GetMapping("/get-post")
    public ResponseEntity<Post> getAllPostByUserId(@RequestParam int postId, @RequestParam String givenToken){
        return postService.getPost(postId,givenToken);
    }
}
