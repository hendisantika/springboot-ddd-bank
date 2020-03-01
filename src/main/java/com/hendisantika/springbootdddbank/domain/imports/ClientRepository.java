package com.hendisantika.springbootdddbank.domain.imports;

import com.hendisantika.springbootdddbank.domain.Client;

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

    /**
     * Saves a client giving it a unique, higher ID
     *
     * @param client the {@link Client} to be saved
     * @return the modified instance
     */
    Client save(Client client);
}
