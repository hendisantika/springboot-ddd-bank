package com.hendisantika.springbootdddbank.rest;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-ddd-bank
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 07/03/20
 * Time: 21.15
 */

import com.hendisantika.springbootdddbank.domain.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

/**
 * A Spring Web MVC controller offering a REST service for accessing all
 * external operations of the application.
 */
@RestController
// Made transactional according to the answer of Rogério at
// https://stackoverflow.com/questions/23118789/why-we-shouldnt-make-a-spring-mvc-controller-transactional
@Transactional
// If you want to make this REST service accessible by another site or port, you
// must allow Cross-Origin Resource Sharing (CORS).
// In Spring you can mark this controller class e.g. as @CrossOrigin(origins =
// "http://localhost:9000") if the web pages are served on port 9000.
// See an example at https://spring.io/guides/gs/rest-service-cors/ and the
// documentation of annotation @CrossOrigin.
// @CrossOrigin
public class ApplicationController {
    private final BankService bankService;

    private final String className = getClass().getSimpleName();

    @Autowired
    public ApplicationController(final BankService bankService) {
        this.bankService = bankService;
    }

    /*
     * A good resource for the design of REST URIs is
     * https://blog.mwaysolutions.com/2014/06/05/10-best-practices-for-better-
     * restful-api/ But for simplification of access control we will group the URIs
     * by the roles, which may access them. So URIs starting with /bank are for
     * bankers, URIs starting with /client are for clients. For further
     * simplification we will not include the username of a Client into his URI, but
     * each request to a URI starting with /client will infer the concerned username
     * from the authenticated user.
     */

    // For everyone (guests):

    @GetMapping(path = "/")
    public ResponseEntity<String> home(/* final WebSecurityConfig webSecurityConfig, */final HttpMethod method,
                                                                                       final WebRequest request) {
        _print(method, request);
        final String htmlContent = "<!DOCTYPE html><html><body>"
                + "<h1>Welcome to the Spring DDD Bank REST Webservice.</h1>"
                + "<p style='font-size: large;'>Click here for <a href='swagger-ui.html'>REST API documentation</a> " +
                "powered by <a href='https://swagger.io/'>Swagger</a></p>"
                + "</body></html>";
        final ResponseEntity<String> responseEntity = new ResponseEntity<>(htmlContent, HttpStatus.OK);
        return responseEntity;
    }
}
