package com.vmo.springdemo.demo1.service.impl;

import com.vmo.springdemo.demo1.configuration.MyConstants;
import com.vmo.springdemo.demo1.models.User;
import com.vmo.springdemo.demo1.service.MailService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
@Component
public class MailServiceImpl implements MailService {
    @Autowired
    public JavaMailSender javaMailSender;
    @SneakyThrows
    @Override
    public void sendMail(User user) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

        String htmlMsg = " <h3>Hi " + user.getUsername() + " , the order you placed was successful !!! </h3> "
                + "<img src='https://shopta.vn/images/2015/11/dat-hang-thanh-cong.jpg'>";

        message.setContent(htmlMsg, "text/html");

//        FileSystemResource file = new FileSystemResource(new File("test.txt"));
//        helper.addAttachment("Demo Mail", file);
        helper.setFrom(MyConstants.MY_EMAIL);
        helper.setTo(user.getEmail());

        helper.setSubject("ORDER NOTICE");
        this.javaMailSender.send(message);
    }
}
