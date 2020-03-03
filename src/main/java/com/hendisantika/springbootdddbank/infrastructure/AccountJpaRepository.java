package com.hendisantika.springbootdddbank.infrastructure;

import com.hendisantika.springbootdddbank.domain.Account;
import com.hendisantika.springbootdddbank.domain.AccountNo;
import com.hendisantika.springbootdddbank.domain.imports.AccountRepository;
import com.hendisantika.springbootdddbank.infrastructure.imports.ImportedAccountJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-ddd-bank
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 01/03/20
 * Time: 06.56
 */
@Service
public class AccountJpaRepository implements AccountRepository {

    private final ImportedAccountJpaRepository impl;

    @Autowired
    public AccountJpaRepository(final ImportedAccountJpaRepository impl) {
        this.impl = impl;
    }

    @Override
    public Optional<Account> find(AccountNo acccountNo) {
        return impl.findOneById(acccountNo.toLong());
    }

    public void deleteAll() {
        impl.deleteAll();
    }


    public Account save(final Account account) {
        return impl.save(account);
    }
}
