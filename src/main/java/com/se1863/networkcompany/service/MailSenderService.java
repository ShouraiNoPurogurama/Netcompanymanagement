package com.se1863.networkcompany.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.se1863.networkcompany.enums.EmailTemplate;

@Service
public class MailSenderService {
    @Autowired
    private JavaMailSender mailSender;
    EmailTemplate emailTemplate;

    public void sendEmail(String toEmail, String subject, String body, String typeConst) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper;

        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom("tna71215@gmail.com");
            helper.setTo(toEmail);
            helper.setText(generateHTMLContent(body, typeConst), true);
            helper.setSubject(subject);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        System.out.println("Mail sent successfully to." + toEmail);
    }

    public String generateHTMLContent(String body, String typeConst) {
        EmailTemplate mailTemplate = EmailTemplate.valueOf(typeConst);
        String template = mailTemplate.getTemplate();
        return template;
    }
}
