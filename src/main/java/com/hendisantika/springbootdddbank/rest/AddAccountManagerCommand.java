package com.hendisantika.springbootdddbank.rest;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-ddd-bank
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 05/03/20
 * Time: 06.42
 */

/**
 * Command to add the Client with the given username as a manager to the Account with the given accountNo.
 */
public class AddAccountManagerCommand {
    public Long accountNo;
    public String username;

    @Override
    public String toString() {
        return String.format(
                "AddAccountManagerCommand{accountNo=%d, username=%s}",
                accountNo, username);
    }
}
