package QienApp.qien.security.rest;

import QienApp.qien.controller.GebruikerService;
import QienApp.qien.domein.Gebruiker;
import QienApp.qien.security.domein.GebruikerPrincipal;
import QienApp.qien.security.service.GebruikerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth-examples")
public class AuthExamplesEndpoints {
    @Autowired
    private GebruikerDetailsService gebruikerDetailsService;

    @Autowired
    private GebruikerService gebruikerService;

    @GetMapping("/user/{id}")
    // In deze methode zie je hoe je de met Spring Security ingelogde
    // gebruiker kunt opvragen en bijvoorbeeld returnen naar de front-end
    public Gebruiker getGebruiker(@PathVariable Long id,
                                  Authentication authentication) {
        Gebruiker g = ((GebruikerPrincipal) authentication.getPrincipal())
                      .getGebruiker();
        return g;
    }

    @PostMapping
    public String doNothingOnPost() {
        return null;
    }

}
