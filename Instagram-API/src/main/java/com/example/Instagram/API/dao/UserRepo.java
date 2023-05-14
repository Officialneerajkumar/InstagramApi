package com.example.Instagram.API.dao;

import com.example.Instagram.API.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    User findFirstByEmail(String email);
}
