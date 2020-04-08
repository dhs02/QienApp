package QienApp.qien.security.domein;

import QienApp.qien.domein.Admin;
import QienApp.qien.domein.Contactpersoon;
import QienApp.qien.domein.Gebruiker;
import QienApp.qien.domein.Medewerker;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class GebruikerPrincipal implements UserDetails {
    private Gebruiker gebruiker;

    public GebruikerPrincipal(Gebruiker gebruiker) {
        this.gebruiker = gebruiker;
    }

    public Gebruiker getGebruiker() {
        return this.gebruiker;
    }

    public void setGebruiker(Gebruiker gebruiker) {
        this.gebruiker = gebruiker;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities= new ArrayList<>();

        if (this.gebruiker instanceof Admin) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        if (this.gebruiker instanceof Contactpersoon) {
            authorities.add(new SimpleGrantedAuthority("ROLE_CONTACTPERSOON"));
        }

        if (this.gebruiker instanceof Medewerker) {
            authorities.add(new SimpleGrantedAuthority("ROLE_MEDEWERKER"));
        }

        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.gebruiker.getWachtwoordHash();
    }

    @Override
    public String getUsername() {
        return this.gebruiker.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
