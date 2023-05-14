package com.example.Instagram.API.service;

import com.example.Instagram.API.dao.TokenRepo;
import com.example.Instagram.API.dao.UserRepo;
import com.example.Instagram.API.dto.*;
import com.example.Instagram.API.model.AuthenticationToken;
import com.example.Instagram.API.model.User;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AuthenticationTokenService authService;
    @Autowired
    private TokenRepo tokenRepo;
    public SignUpOutput signUp(SignUpInput signUpInput) {

        User user = userRepo.findFirstByEmail(signUpInput.getEmail());
        // check user exist or not
        if(user != null){
            throw new IllegalStateException("User already exists!!!!...sign in instead");
        }
        // incryption

        String encryptedPassword = null;
        try {
            encryptedPassword = encryptPassword(signUpInput.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // save the user
        user = new User(signUpInput.getFirstname(),signUpInput.getLastName(),signUpInput.getAge(),signUpInput.getEmail(),signUpInput.getPassword(),signUpInput.getPhoneNumber());

        userRepo.save(user);

        //token creation and saving
        AuthenticationToken token = new AuthenticationToken(user);

        authService.saveToken(token);

        return new SignUpOutput("User registered","User created successfully");
    }
    private String encryptPassword(String password)throws NoSuchAlgorithmException  {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(password.getBytes());
        byte[] digested =  md5.digest();

        String hash = DatatypeConverter.printHexBinary(digested);
        return hash;
    }

    public SignInOutput signIn(SignInInput signInInput) {

        User user = userRepo.findFirstByEmail(signInInput.getEmail());

        if(user == null){
            throw new IllegalStateException("Invalid user !!!!...sign up instead");
        }

        String encryptedPassword = null;

        try {
            encryptedPassword = encryptPassword(signInInput.getPassword());
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

        }

        // get match with the save encrypted password
        boolean isPasswordValid = encryptedPassword.equals(user.getPassword());

        if(!isPasswordValid)
        {
            throw new IllegalStateException("User invalid!!!!...sign up instead");
        }
        //figure out the token

        AuthenticationToken authToken = authService.getToken(user);

        //set up output response

        return new SignInOutput("Authentication Successfull !!!",authToken.getToken());

    }

    public UpdateOutput updateUser(UpdateInput updateInput,String givenToken) {

        String status = null;
        String message = null;
        //authenticate
        if(authService.authenticate(givenToken)){
            AuthenticationToken authToken = tokenRepo.findByToken(givenToken);
            User user = authToken.getUser();
            if(updateInput.getFirstName()!=null){
                user.setFirstName(updateInput.getFirstName());
            }
            if(updateInput.getLastName()!=null){
                user.setLastName(updateInput.getLastName());
            }
            if(updateInput.getAge()!=null){
                user.setAge(updateInput.getAge());
            }
            if(updateInput.getEmail()!=null){
                user.setEmail(updateInput.getEmail());
            }
            if(updateInput.getPassword()!=null){
                user.setPassword(updateInput.getPassword());
            }
            if(updateInput.getPhoneNumber()!=null){
                user.setPhoneNumber(updateInput.getPhoneNumber());
            }
            status = "Authentication Successfull !!!";
            message = "User updated successfully !!";

        }else{
            status = "Authentication unSuccessfull !!!";
            message = "Invalid user !!!";
        }
        return new UpdateOutput(status,message);
    }
}
