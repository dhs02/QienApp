package QienApp.qien.controller;

import QienApp.qien.domein.EmailBericht;
import QienApp.qien.domein.EmailCfg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    EmailCfg emailCfg;
//    @Autowired
//    EmailBericht emailBericht;

    public void mailVersturen(){
        System.out.println("email versturen");


        // Create a mail sender
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.emailCfg.getHost());
        mailSender.setPort(this.emailCfg.getPort());
        mailSender.setUsername(this.emailCfg.getUsername());
        mailSender.setPassword(this.emailCfg.getPassword());

        // Create an email instance
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("rubenvanrij@gmail.com");
        mailMessage.setTo("rubenvanrij@gmail.com");
        mailMessage.setSubject("Goedkeuring vereist ");
        mailMessage.setText("jojo");

        // Send mail
        mailSender.send(mailMessage);

        System.out.println("Mail sent");
    }
}
