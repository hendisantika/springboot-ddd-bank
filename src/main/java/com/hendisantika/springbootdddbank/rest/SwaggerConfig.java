package com.hendisantika.springbootdddbank.rest;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-ddd-bank
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 05/03/20
 * Time: 06.46
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private WebSecurityConfig webSecurityConfig;

    public SwaggerConfig(final WebSecurityConfig webSecurityConfig) {
        this.webSecurityConfig = webSecurityConfig;
    }
}
