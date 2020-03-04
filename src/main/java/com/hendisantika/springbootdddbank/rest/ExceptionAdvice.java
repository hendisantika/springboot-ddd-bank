package com.hendisantika.springbootdddbank.rest;

import com.hendisantika.springbootdddbank.domain.Client;
import multex.Msg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.mediatype.vnderrors.VndErrors;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.ResourceBundle;

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

    @ResponseBody
    @ExceptionHandler({Exception.class})
    /**
     * Reports the given Exception with messages in three ways:
     * <ol>
     * <li>with messages in default language and with stack trace to the error
     * log</li>
     * <li>with localized messages according to the given Locale of the web request
     * to the REST client</li>
     * <li>as HTTP status code to the REST client</li>
     * </ol>
     */
    VndErrors reportException(final Exception ex, final Locale requestLocale, final HttpServletResponse response) {
        // prepare messages for REST client with the Locale of the request:
        /** Message texts for exceptions. */
        final ResourceBundle requestResourceBundle = ResourceBundle.getBundle(BASE_NAME, requestLocale);
        final StringBuffer clientMessages = new StringBuffer();
        multex.Msg.printMessages(clientMessages, ex, requestResourceBundle);
        final String clientMesagesString = clientMessages.toString();

        // prepare log report with messages and stack trace:
        final StringBuffer serverMessages = new StringBuffer();
        serverMessages.append("Processing REST request threw exception:\n");
        final Locale defaultLocale = Locale.getDefault();
        final ResourceBundle defaultResourceBundle = ResourceBundle.getBundle(BASE_NAME, defaultLocale);
        if (!defaultResourceBundle.equals(requestResourceBundle)) {
            serverMessages.append(clientMesagesString);
            serverMessages.append("\n-----\n");
        }
        Msg.printReport(serverMessages, ex, defaultResourceBundle);

        // log the report on the server:
        log.error(serverMessages.toString());
        // respond with localized messages to the client:
        response.setStatus(exceptionToStatus(ex).value());
        return new VndErrors("error", clientMesagesString);
    }

    final String restInterfacePackagePrefix = _computePackagePrefix(ApplicationController.ClientCreateWithIdExc.class);
    final String domainPackagePrefix = _computePackagePrefix(Client.NotOwnerExc.class);

}
