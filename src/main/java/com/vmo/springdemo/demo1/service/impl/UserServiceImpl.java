package com.vmo.springdemo.demo1.service.impl;

import com.vmo.springdemo.demo1.models.User;
import com.vmo.springdemo.demo1.repository.UserRepository;
import com.vmo.springdemo.demo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Transactional(rollbackFor = Exception.class)
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Transactional
    @Override
    public void transfer(String sender, String receiver, Double amount) {
        deduct(sender, amount);
        deposit(receiver, amount);
    }

    @Transactional
    @Override
    public void deduct(String sender, double amount) {
        User fromUser = userRepository.findByUsername(sender).get();
        double coin = fromUser.getCoin();
        if (coin<amount){
            throw new IllegalStateException("Not enough money");
        } else {
            fromUser.setCoin(coin - amount);
            userRepository.save(fromUser);
        }
    }
    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void deposit(String receiver, double amount) {
        User toUser = userRepository.findByUsername(receiver).get();
        toUser.setCoin(toUser.getCoin() + amount);
        userRepository.save(toUser);
        if (new Random().nextBoolean()) {
            throw new RuntimeException("DummyException: this should cause rollback of both inserts!");
        }
    }
}
