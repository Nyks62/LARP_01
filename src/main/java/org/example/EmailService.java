package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmailService {

   /* @Autowired
    private JavaMailSender emailSender;

    public void sendConventEmail(String userEmail, String conventName, Date conventDate, String tag) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userEmail);
        message.setSubject("Hej! Tym konwentem możesz być zainteresowany: " + conventName);
        message.setText("Data: " + conventDate.toString() + "\nTag: " + tag);
        emailSender.send(message);
    } */
}
