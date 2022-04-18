package com.vmo.springdemo.demo1.service;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void transfer(String sender, String receiver, Double amount);
    void deduct(String sender, double amount);
    void deposit(String receiver, double amount);
}
