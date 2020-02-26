package com.hendisantika.springbootdddbank.domain;

import com.hendisantika.springbootdddbank.domain.base.EntityBase;

import javax.persistence.Entity;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-ddd-bank
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 27/02/20
 * Time: 06.54
 */
@Entity
public class Account extends EntityBase<Account> {

    private String name;
    private Amount balance = new Amount(0);

    /**
     * Necessary for JPA entities internally.
     */
    @SuppressWarnings("unused")
    private Account() {
    }

    public Account(final String name) {
        this.name = name;
    }

    /**
     * Returns the minimum balance for the application.
     *
     * @return The minimum balance, which must stay on each account.
     */
    public static final Amount getMinimumBalance() {
        return new Amount(-1000, 0);
    }

    public AccountNo accountNo() {
        return new AccountNo(getId());
    }

    @Override
    public String toString() {
        return String.format("Account{accountNo=%d, name='%s', balance='%s'}", accountNo(), name, balance);
    }

    public String getName() {
        return name;
    }

    public Amount getBalance() {
        return balance;
    }

    void setBalance(final Amount amount) {
        this.balance = amount;
    }

}
