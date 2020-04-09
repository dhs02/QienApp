package QienApp.qien.rest;

import QienApp.qien.controller.MailService;
import QienApp.qien.controller.urenform.UrenDeclaratieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import QienApp.qien.domein.EmailBericht;
import QienApp.qien.domein.EmailCfg;
import QienApp.qien.domein.urenform.Urendeclaratie;

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
    @Autowired
    private UrenDeclaratieService urenDeclaratieService;

    @GetMapping("/{cid}/{uid}/")
    public void getEmail(@PathVariable(value = "cid") long contactpersoonId, @PathVariable(value = "uid") long urendeclaratieId) {
        System.out.println(contactpersoonId);
        mailService.mailVersturen(contactpersoonId, urendeclaratieId);
    }
    
    /**MICHIELS TESTMETHODE
     * 
     * @param emailBericht
     * @param bindingResult
     */
    @GetMapping("/{uid}/")
    public void maakMeeltje(@PathVariable(value = "uid") long urendeclaratieId) {
    	
    	Urendeclaratie u = urenDeclaratieService.getUrendeclaratie(urendeclaratieId);
    	System.out.println("udec id" + urendeclaratieId);
    	long contactpersoonId = u.getMedewerker().getContactpersoon().getId();
    	System.out.println("gelukt! contactpersoon id" + contactpersoonId);
    	
        mailService.mailVersturen(contactpersoonId, urendeclaratieId);
    }
	

    @PostMapping
    public void sendFeedback(@RequestBody EmailBericht emailBericht,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Feedback is not valid");
        }
    }
}