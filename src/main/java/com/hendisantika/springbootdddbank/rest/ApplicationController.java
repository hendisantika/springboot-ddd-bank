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

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

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
}
