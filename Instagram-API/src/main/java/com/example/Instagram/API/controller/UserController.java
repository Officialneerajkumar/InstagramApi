package com.example.Instagram.API.controller;

import com.example.Instagram.API.dto.*;
import com.example.Instagram.API.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    //signUp
      //.1 signUpInput
      //.2 signUpOutput
    @PostMapping("/sign-up")
    public SignUpOutput signUp(@RequestBody SignUpInput signUpInput){
        return userService.signUp(signUpInput);
    }

    @PostMapping("/sign-in")
    public SignInOutput signIn(@RequestBody SignInInput signInInput){
        return userService.signIn(signInInput);
    }

    @PutMapping("/update-user")
    public UpdateOutput updateUser(@RequestBody UpdateInput updateInput,@RequestParam String givenToken){
        return userService.updateUser(updateInput,givenToken);
    }

}
