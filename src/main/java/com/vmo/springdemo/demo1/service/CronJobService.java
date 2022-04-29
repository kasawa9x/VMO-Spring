package com.vmo.springdemo.demo1.service;

import com.vmo.springdemo.demo1.configuration.MyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class CronJobService {
    @Autowired
    public JavaMailSender javaMailSender;

    //    @Scheduled(fixedDelay = 1 * 1000 * 60)
    @Scheduled(cron = "10 * * * * ?")
//    @Async
    void sendMailCronjob() throws MessagingException {
        System.out.println("sendHtmlEmail");

        MimeMessage message = javaMailSender.createMimeMessage();

        boolean multipart = true;

        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");

        String htmlMsg = "<h3> Apple store have new product, come to buy !!! </h3> "
                + "<img src='https://www.brainybeaver.com/wp-content/uploads/2017/06/apple-store-logo.jpg'>";

        message.setContent(htmlMsg, "text/html");

        helper.setFrom(MyConstants.MY_EMAIL);

        helper.setTo(MyConstants.FRIEND_EMAIL);

        helper.setSubject("Test send HTML email");

        System.out.println("sendHtmlEmail: " + helper);

        this.javaMailSender.send(message);
    }
}
