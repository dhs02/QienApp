package QienApp.qien.security.service;

import QienApp.qien.controller.GebruikerRepository;
import QienApp.qien.domein.Gebruiker;
import QienApp.qien.security.domein.GebruikerPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/* Guide on Spring Security authentication with a
 * custom database-backed UserDetailsService:
 * https://www.baeldung.com/spring-security-authentication-with-a-database
 */
@Service
public class GebruikerDetailsService implements UserDetailsService {

    @Autowired
    private GebruikerRepository gebruikerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<Gebruiker> gebruikerOptional = this
                .gebruikerRepository
                .findByEmail(email);

        if (!gebruikerOptional.isPresent()){
            throw new UsernameNotFoundException(email);
        }
        Gebruiker gebruiker = gebruikerOptional.get();

        return new GebruikerPrincipal(gebruiker);
    }
}
