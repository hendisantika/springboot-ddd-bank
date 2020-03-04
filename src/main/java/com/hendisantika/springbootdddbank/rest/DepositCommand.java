package com.hendisantika.springbootdddbank.rest;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-ddd-bank
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 05/03/20
 * Time: 06.50
 */

/**
 * Command to deposit the amount in euros to the Account with the given account number.
 */
public class DepositCommand {
    public Long accountNo;
    public double amount;

    @Override
    public String toString() {
        return String.format(
                "DepositCommand{accountNo=%d, amount='%s'}",
                accountNo, amount);
    }
}
