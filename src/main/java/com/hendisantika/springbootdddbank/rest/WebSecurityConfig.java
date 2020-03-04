package com.hendisantika.springbootdddbank.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-ddd-bank
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 05/03/20
 * Time: 06.43
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String CLIENT_ROLE = "CLIENT";
    private static final String BANK_ROLE = "BANK";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/bank/**").hasRole(BANK_ROLE)
                .antMatchers("/client/**").hasRole(CLIENT_ROLE)
                // For swagger-ui. See http://springfox.github
                // .io/springfox/docs/current/#answers-to-common-questions-and-problems
                // No. 26 "Why is http://host:port/swagger-ui.html blank" ...
                .antMatchers(
                        HttpMethod.GET,
                        "/v2/api-docs",
                        "/swagger-resources/**",
                        "/swagger-ui.html**",
                        "/webjars/**",
                        "favicon.ico"
                ).permitAll()
                .anyRequest().authenticated()
                .and().httpBasic() //Authenticate with username and password.
                //For REST services disable CSRF protection.
                //See https://docs.spring.io/spring-security/site/docs/current/reference/html/csrf
                // .html#when-to-use-csrf-protection
                .and().csrf().disable()
        ;
    }

    private static final List<String> predefinedUsernames = Arrays.asList("bank", "naruto", "sakura", "sasuke",
            "kakashi");

    /**
     * Configures the {@link #predefinedUsernames} as known users with their password equal to the user name.
     *
     * @param auth a SecurityBuilder injected by Spring, used to create an AuthenticationManager
     * @throws Exception if an error occurs when configuring the in memory authentication
     */
    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
        final InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> inMemoryAuthentication =
                auth.inMemoryAuthentication();
        for (final String username : predefinedUsernames) {
            final String role = username.equalsIgnoreCase(BANK_ROLE) ? BANK_ROLE : CLIENT_ROLE;
            inMemoryAuthentication.withUser(username).password(username).roles(role);
        }
    }

    public List<String> predefinedUsernames() {
        return predefinedUsernames;
    }

}
