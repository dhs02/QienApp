package QienApp.qien.security.rest;

import QienApp.qien.controller.GebruikerService;
import QienApp.qien.domein.Gebruiker;
import QienApp.qien.security.domein.GebruikerPrincipal;
import QienApp.qien.security.service.GebruikerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth-examples")
public class AuthExamplesEndpoints {
    @Autowired
    private GebruikerDetailsService gebruikerDetailsService;

    @Autowired
    private GebruikerService gebruikerService;

    @GetMapping("/user/{id}")
    public GebruikerPrincipal getGebruikerPrincipal(@PathVariable Long id) {
        Gebruiker gebruiker = this.gebruikerService.findById(id).get();
        return (GebruikerPrincipal) this
                .gebruikerDetailsService
                .loadUserByUsername(gebruiker.getEmail());
    }

    @PostMapping
    public String doNothingOnPost() {
        return null;
    }

}
