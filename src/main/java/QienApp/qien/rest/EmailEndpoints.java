package QienApp.qien.rest;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import QienApp.qien.domein.EmailBericht;
import QienApp.qien.domein.EmailCfg;

import javax.validation.ValidationException;

@RestController
@RequestMapping("/api/email")
public class EmailEndpoints {

    private EmailCfg emailCfg;

    public EmailEndpoints(EmailCfg emailCfg) {
        this.emailCfg = emailCfg;
    }

    @PostMapping
    public void sendFeedback(@RequestBody EmailBericht emailBericht,
                             BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationException("Feedback is not valid");
        }

        // Create a mail sender
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.emailCfg.getHost());
        mailSender.setPort(this.emailCfg.getPort());
        mailSender.setUsername(this.emailCfg.getUsername());
        mailSender.setPassword(this.emailCfg.getPassword());

        // Create an email instance
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(emailBericht.getEmail());
        mailMessage.setTo("Werkgever@nos.nl");
        mailMessage.setSubject("Goedkeuring vereist " + emailBericht.getName());
        mailMessage.setText(emailBericht.getFeedback());

        // Send mail
        mailSender.send(mailMessage);
        
        System.out.println("Mail sended");
    }
}