package com.company.coronavirusReliableInfos_API.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@RestController
@RequestMapping("/api/v1")
public class EmailController {

    @GetMapping(value = "/sendloggs")
    public String sendEmail() throws AddressException, MessagingException, IOException {
        sendmail();
        return "Email sent successfully";
    }

    private void sendmail() throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("bartosz.maleta@gmail.com", "");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("bartosz.maleta@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("bartosz.maleta@gmail.com"));
        msg.setSubject("Loggs report");
        msg.setContent("To whom it may concern,\nHere is loggs report.\n\nBest regards\nReliable infos Inc.", "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("To whom it may concern,\nHere is loggs report.\n\nBest regards\nReliable infos Inc.", "text/html");
        messageBodyPart.setContent("To whom it may concern,\nHere is loggs report.\n\nBest regards\nReliable infos Inc.", "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        MimeBodyPart attachPart = new MimeBodyPart();

        attachPart.attachFile("src/main/resources/loggs/log.out");
        multipart.addBodyPart(attachPart);
        msg.setContent(multipart);
        Transport.send(msg);
    }
}