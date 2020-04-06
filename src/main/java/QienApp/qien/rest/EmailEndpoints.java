package QienApp.qien.rest;

import QienApp.qien.controller.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    private MailService mailService;

    @GetMapping("/{id}")
    public void getEmail(@PathVariable(value = "id") long contactpersoonId) {
        System.out.println(contactpersoonId);
        mailService.mailVersturen(contactpersoonId);
    }

    @PostMapping
    public void sendFeedback(@RequestBody EmailBericht emailBericht,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Feedback is not valid");
        }
    }
}