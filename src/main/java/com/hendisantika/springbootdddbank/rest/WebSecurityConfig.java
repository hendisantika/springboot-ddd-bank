package com.hendisantika.springbootdddbank.rest;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
}
