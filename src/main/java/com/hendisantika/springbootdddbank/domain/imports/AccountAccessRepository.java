package com.hendisantika.springbootdddbank.domain.imports;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-ddd-bank
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 01/03/20
 * Time: 07.09
 */
public interface AccountAccessRepository {
    /**
     * Deletes all {@link AccountAccess} objects. Linked {@link Client}s or
     * {@link Account}s must be deleted before.
     */
    void deleteAll();
}
