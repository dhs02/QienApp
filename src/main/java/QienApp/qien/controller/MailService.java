package QienApp.qien.controller;

import QienApp.qien.domein.EmailBericht;
import QienApp.qien.domein.EmailCfg;
import QienApp.qien.domein.Opdrachtgever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class MailService {

    @Autowired
    OpdrachtgeverRepository opdrachtgeverRepository;
    @Autowired
    EmailCfg emailCfg;
//    @Autowired
//    EmailBericht emailBericht;

    public void mailVersturen(long opdrachtgeverId){
        System.out.println("email versturen");


        Optional <Opdrachtgever> opdrachtgever = opdrachtgeverRepository.findById(opdrachtgeverId);
        boolean a = opdrachtgever.isPresent();
        Opdrachtgever b = opdrachtgever.get();
        System.out.println(b.getEmail());

        System.out.println();
        // Create a mail sender
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.emailCfg.getHost());
        mailSender.setPort(this.emailCfg.getPort());
        mailSender.setUsername(this.emailCfg.getUsername());
        mailSender.setPassword(this.emailCfg.getPassword());

        // Create an email instance
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("rubenvanrij@gmail.com");
        mailMessage.setTo(b.getEmail());
        mailMessage.setSubject("Goedkeuring vereist " + b.getBedrijfsnaam());
        mailMessage.setText("" + opdrachtgeverId);

        // Send mail
        mailSender.send(mailMessage);

        System.out.println("Mail sent");
    }
}
