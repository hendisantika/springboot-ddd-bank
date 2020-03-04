package com.hendisantika.springbootdddbank.rest;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-ddd-bank
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 05/03/20
 * Time: 06.46
 */

/**
 * Command to transfer the amount in euros from the Account with the given sourceAccountNo to the Account with the
 * given destinationAccountNo.
 */
public class TransferCommand {
    public Long sourceAccountNo;
    public Long destinationAccountNo;
    public double amount;

    @Override
    public String toString() {
        return String.format(
                "TransferCommand{sourceAccountNo=%d, destinationAccountNo=%d, amount=%s}",
                sourceAccountNo, destinationAccountNo, amount);
    }
}
