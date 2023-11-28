package com.integrationProject.managementTrainer.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class Email {


    private final JavaMailSender emailsender;


    public void forgotPassword(String to ,String subject , String password) throws MessagingException, jakarta.mail.MessagingException {

        MimeMessage message=emailsender.createMimeMessage();

        MimeMessageHelper helper=new MimeMessageHelper(message,true);
        helper.setFrom("Tunisia.Stage@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);
        String htmlMsg="<p><b>Your Login details for Intern Management Platform Platform </b><br><b>Email: </b> " + to + " <br><b>Password: </b> " + password + "<br><a href=\"http://localhost:4200/\">Click here to login</a></p>";
        message.setContent(htmlMsg,"text/html");
        emailsender.send(message);


    }



}
