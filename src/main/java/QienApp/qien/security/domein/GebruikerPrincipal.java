package QienApp.qien.security.domein;

import QienApp.qien.domein.Gebruiker;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

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
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
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
