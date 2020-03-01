package com.hendisantika.springbootdddbank.domain.imports;

import com.hendisantika.springbootdddbank.domain.Account;
import com.hendisantika.springbootdddbank.domain.AccountNo;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-ddd-bank
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 02/03/20
 * Time: 06.53
 */
public interface AccountRepository {
    /**
     * Searches the {@link Account} object with the given account number.
     *
     * @param acccountNo unique account number of the searched account
     * @return the {@link Account} object with the given account number, if existing.
     * @throws IllegalArgumentException acccountNo is null or empty
     */
    Optional<Account> find(AccountNo acccountNo);
}
