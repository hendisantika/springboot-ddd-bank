package com.hendisantika.springbootdddbank.infrastructure;

import com.hendisantika.springbootdddbank.domain.imports.ClientRepository;
import com.hendisantika.springbootdddbank.infrastructure.imports.ImportedClientJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-ddd-bank
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 04/03/20
 * Time: 06.44
 */
@Service
public class ClientJpaRepository implements ClientRepository {

    private final ImportedClientJpaRepository impl;

    @Autowired
    public ClientJpaRepository(final ImportedClientJpaRepository impl) {
        this.impl = impl;
    }

    public void deleteAll() {
        impl.deleteAll();
    }
}
