package com.example.Instagram.API.dao;

import com.example.Instagram.API.model.AuthenticationToken;
import com.example.Instagram.API.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepo extends JpaRepository<AuthenticationToken,Integer> {
    AuthenticationToken findByUser(User user);

    AuthenticationToken findByToken(String givenToken);
}
