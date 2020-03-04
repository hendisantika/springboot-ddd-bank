package com.hendisantika.springbootdddbank.rest;

import com.hendisantika.springbootdddbank.domain.Account;
import com.hendisantika.springbootdddbank.domain.AccountAccess;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-ddd-bank
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 05/03/20
 * Time: 06.41
 */
public class AccountAccessResource {
    public String clientUsername;
    public boolean isOwner;
    public Long accountNo;
    public String accountName;
    public String accountBalance;

    /**
     * Constructs a data transfer object from the given domain entity.
     *
     * @param entity the entity to be converted
     */
    public AccountAccessResource(final AccountAccess entity) {
        final Account account = entity.getAccount();
        this.clientUsername = entity.getClient().getUsername();
        this.isOwner = entity.isOwner();
        this.accountNo = account.accountNo().toLong();
        this.accountName = account.getName();
        this.accountBalance = Double.toString(account.getBalance().toDouble());
    }
}
