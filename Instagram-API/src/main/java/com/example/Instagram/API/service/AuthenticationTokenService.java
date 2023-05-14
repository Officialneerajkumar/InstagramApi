package com.example.Instagram.API.service;

import com.example.Instagram.API.dao.TokenRepo;
import com.example.Instagram.API.model.AuthenticationToken;
import com.example.Instagram.API.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationTokenService {

    @Autowired
    private TokenRepo tokenRepo;
    public void saveToken(AuthenticationToken token) {
        tokenRepo.save(token);
    }

    public AuthenticationToken getToken(User user) {
        return tokenRepo.findByUser(user);
    }

    public boolean authenticate(String givenToken) {
        AuthenticationToken authToken = tokenRepo.findByToken(givenToken);
        return authToken.getToken().equals(givenToken);
    }
}
