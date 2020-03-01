package com.hendisantika.springbootdddbank.domain.imports;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-ddd-bank
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 02/03/20
 * Time: 06.55
 */
public interface ClientRepository {
    /**
     * Deletes all Clients. Useful for test scenarios in order to start with an
     * empty {@link Client} set.
     */
    void deleteAll();
}
