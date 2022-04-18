package com.vmo.springdemo.demo1.service;

import com.vmo.springdemo.demo1.models.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    Optional<User> findByName(String name) ;
    void transfer(String sender, String receiver, Double amount);
    void deduct(String sender, double amount);
    void deposit(String receiver, double amount);
}
