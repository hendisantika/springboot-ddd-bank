package com.hendisantika.springbootdddbank.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-ddd-bank
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 05/03/20
 * Time: 06.48
 */

/**
 * Centralized Exception Reporting for all Controller classes.
 *
 * @author Christoph Knabe
 * @see <a href=
 * "https://spring.io/guides/tutorials/bookmarks/#_building_a_hateoas_rest_service"
 * > Building a HATEOAS REST Service</a> with Spring.
 * @see <a href=
 * "https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc"
 * > Exception Handling in Spring MVC</a>.
 */
@ControllerAdvice
public class ExceptionAdvice {
    /**
     * The baseName for locating the exception message text resource bundle.
     */
    public static final String BASE_NAME = "MessageText";

    private static final Logger log = LoggerFactory.getLogger(ExceptionAdvice.class);

}
