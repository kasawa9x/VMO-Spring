package com.vmo.springdemo.demo1.service.impl;

import com.vmo.springdemo.demo1.configuration.MyConstants;
import com.vmo.springdemo.demo1.service.CronJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class CronJobServiceImpl implements CronJobService {
    @Autowired
    public JavaMailSender javaMailSender;
    @Scheduled(fixedDelay = 1 * 1000 * 60)
//    @Scheduled(cron = "*/1 * * * * ?")
//    @Async
    @Override
    public void sendMailCronjob() throws MessagingException {
            System.out.println("sendHtmlEmail");

            MimeMessage message = javaMailSender.createMimeMessage();

            boolean multipart = true;

            MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");

            String htmlMsg = "<img src='https://www.brainybeaver.com/wp-content/uploads/2017/06/apple-store-logo.jpg>'";

            message.setContent(htmlMsg, "text/html");

            helper.setFrom(MyConstants.MY_EMAIL);

            helper.setTo(MyConstants.FRIEND_EMAIL);

            helper.setSubject("Test send HTML email");

            System.out.println("sendHtmlEmail: " + helper);

            this.javaMailSender.send(message);

    }
}
