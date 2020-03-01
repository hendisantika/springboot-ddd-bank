package com.hendisantika.springbootdddbank.infrastructure;

import com.hendisantika.springbootdddbank.domain.AccountAccess;
import com.hendisantika.springbootdddbank.infrastructure.imports.ImportedAccountAccessJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-ddd-bank
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 01/03/20
 * Time: 06.55
 */
@Service
public class AccountAccessJpaRepository implements AccountAccessRepository {

    private final ImportedAccountAccessJpaRepository impl;

    @Autowired
    public AccountAccessJpaRepository(final ImportedAccountAccessJpaRepository impl) {
        this.impl = impl;
    }

    public void deleteAll() {
        impl.deleteAll();
    }

    public AccountAccess save(final AccountAccess accountAccess) {
        return impl.save(accountAccess);
    }

    @Override
    public void delete(AccountAccess accountAccess) {
        impl.delete(accountAccess);
    }
}
