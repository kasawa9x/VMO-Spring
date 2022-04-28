package com.vmo.springdemo.demo1.service;

import com.vmo.springdemo.demo1.models.User;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public interface MailService {
    void sendMail( User user) throws MessagingException;
}
