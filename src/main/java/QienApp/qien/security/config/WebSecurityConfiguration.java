package QienApp.qien.security.config;

import QienApp.qien.controller.GebruikerService;
import QienApp.qien.domein.Admin;
import QienApp.qien.domein.Gebruiker;
import QienApp.qien.security.service.GebruikerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/* Comprehensive guide on JWT-based authentication and authorisation
 * with Spring Security for RESTful APIs:
 * https://www.toptal.com/java/rest-security-with-jwt-spring-security-and-java
 */
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private GebruikerDetailsService gebruikerDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
                .userDetailsService(gebruikerDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    // Add endpoints to secure below:
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests(authorize -> authorize
                        .antMatchers("/css/**", "/index").permitAll()

                        .antMatchers(HttpMethod.GET, "/api/gebruikers/**").authenticated()
                        .antMatchers(HttpMethod.POST, "/api/gebruikers/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.PUT, "/api/gebruikers/me").hasRole("USER")
                        .antMatchers(HttpMethod.PUT, "/api/gebruikers/**").hasRole("USER")
                        .antMatchers(HttpMethod.DELETE, "/api/gebruikers/**").hasRole("ADMIN")

                        .antMatchers(HttpMethod.GET, "/api/admins/**").authenticated()
                        .antMatchers(HttpMethod.POST, "/api/admins/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.PUT, "/api/admins/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/api/admins/**").hasRole("ADMIN")

                        .antMatchers(HttpMethod.GET, "/api/medewerkers/**").authenticated()
                        .antMatchers(HttpMethod.POST, "/api/medewerkers/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.PUT, "/api/medewerkers/me").hasRole("MEDEWERKER")
                        .antMatchers(HttpMethod.PUT, "/api/medewerkers/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/api/medewerkers/**").hasRole("ADMIN")

                        .antMatchers(HttpMethod.GET, "/api/contactpersonen/**").authenticated()
                        .antMatchers(HttpMethod.POST, "/api/contactpersonen/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.PUT, "/api/contactpersonen/me").hasRole("CONTACTPERSOON")
                        .antMatchers(HttpMethod.PUT, "/api/contactpersonen/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/api/contactpersonen/**").hasRole("ADMIN")

                        .antMatchers(HttpMethod.GET, "/api/opdrachtgevers/**").authenticated()
                        .antMatchers(HttpMethod.POST, "/api/opdrachtgevers/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.PUT, "/api/opdrachtgevers/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/api/opdrachtgevers/**").hasRole("ADMIN")

                        .antMatchers(HttpMethod.GET, "/api/urendeclaraties/**").authenticated()
                        .antMatchers(HttpMethod.POST, "/api/urendeclaraties/**").authenticated()
                        .antMatchers(HttpMethod.PUT, "/api/urendeclaraties/**").authenticated()
                        .antMatchers(HttpMethod.DELETE, "/api/urendeclaraties/**").hasRole("ADMIN")
                );

        http
                .csrf().disable()
                .formLogin().disable();
    }

}
