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

import com.hendisantika.springbootdddbank.domain.Amount;
import com.hendisantika.springbootdddbank.domain.BankService;
import com.hendisantika.springbootdddbank.domain.Client;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import multex.Exc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.util.List;

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

    // For the banker role all URIs under /bank:

    /*
     * A transaction, which creates two random objects of type Client, but sometimes
     * fails after the first one.
     */
    @ApiOperation(value = "Creates 2 random clients, sometimes fails after first. "
            + "Returns a list of all clients. This is useful for populating the database "
            + "and for checking, if the transaction rollback mechanism works.", authorizations = {
            @Authorization(value = "basicAuth")})
    @PostMapping("/bank/pair")
    public ResponseEntity<ClientResource[]> create2Clients(@ApiParam(hidden = true) final HttpMethod method,
                                                           final WebRequest request) {
        _print(method, request);
        final long now = System.currentTimeMillis();
        final long number = now % 100;
        final Client client1 = bankService.createClient("hans" + number, _randomClientBirthDate());
        System.out.printf("Client %s created.\n", client1);
        if (number % 3 == 0) {
            throw new Exc("Exception after creating {0}. Should have been rolled back.", client1);
        }
        final Client client2 = bankService.createClient("jana" + number, _randomClientBirthDate());
        System.out.printf("Client %s created.\n", client2);
        final List<Client> clients = bankService.findAllClients();
        return _clientsToResources(clients);
    }

    @ApiOperation(value = "Create a client from the passed client resource.", authorizations = {
            @Authorization(value = "basicAuth")})
    @PostMapping("/bank/client")
    public ResponseEntity<ClientResource> createClient(@RequestBody final ClientResource clientResource,
                                                       @ApiParam(hidden = true) final HttpMethod method,
                                                       final WebRequest request) {
        _print(method, request);
        if (clientResource.id != null) {
            throw create(ClientCreateWithIdExc.class, clientResource.username, clientResource.id);
        }
        final LocalDate birthLocalDate = LocalDate.parse(clientResource.birthDate, Util.MEDIUM_DATE_FORMATTER);
        final Client client = bankService.createClient(clientResource.username, birthLocalDate);
        return new ResponseEntity<>(new ClientResource(client), HttpStatus.CREATED);
    }

    /**
     * The client to be created with username {0} must not have an ID, but has {1}
     */
    @SuppressWarnings("serial")
    public static class ClientCreateWithIdExc extends multex.Exc {
    }


    @ApiOperation(value = "Delete the client with the given username.", authorizations = {
            @Authorization(value = "basicAuth")})
    @DeleteMapping("/bank/client/{username}")
    public ResponseEntity<String> deleteClient(@PathVariable @ApiParam("username of client") final String username,
                                               @ApiParam(hidden = true) final HttpMethod method,
                                               final WebRequest request) {
        _print(method, request);
        final Client client = bankService.findClient(username);
        bankService.deleteClient(client);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Find clients. Omit Parameters to retrieve all clients.", authorizations = {
            @Authorization(value = "basicAuth")})
    @GetMapping(path = "/bank/client")
    public ResponseEntity<ClientResource[]> findClients(
            @ApiParam("Returns all clients born at fromBirth or later.") @RequestParam(name = "fromBirth",
                    defaultValue = "") final String fromBirth,
            @ApiParam("Returns all clients with an account with a balance of minBalance or more.") @RequestParam(name = "minBalance", defaultValue = "") final String minBalance,
            @ApiParam(hidden = true) final HttpMethod method, final WebRequest request) {
        _print(method, request);
        final List<Client> clients;
        if ("".equals(fromBirth) && "".equals(minBalance)) {
            clients = bankService.findAllClients();
        } else if ("".equals(minBalance)) { // only fromBirth given
            final LocalDate fromBirthLocalDate = LocalDate.parse(fromBirth, Util.MEDIUM_DATE_FORMATTER);
            clients = bankService.findYoungClients(fromBirthLocalDate);
        } else if (fromBirth.equals("")) { // only minBalance given
            final double minBalanceDouble = Double.parseDouble(minBalance);
            final Amount minBalanceAmount = new Amount(minBalanceDouble);
            clients = bankService.findRichClients(minBalanceAmount);
        } else {
            throw new Exc("Must not provide both parameters: fromBirth and minBalance!");
        }
        return _clientsToResources(clients);
    }
}
