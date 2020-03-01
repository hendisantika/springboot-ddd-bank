package com.hendisantika.springbootdddbank.infrastructure.imports;

import com.hendisantika.springbootdddbank.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-ddd-bank
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 01/03/20
 * Time: 07.01
 */
public interface ImportedAccountJpaRepository extends JpaRepository<Account, Long> {

    void deleteAll();

    Optional<Account> findOneById(Long id);

    Account save(Account account);

    List<Account> findAllByOrderByIdAsc();

    /*
    Iterable<Client> findAllBornFrom(LocalDate minDate);
    Optional<Client> findFirstByOrderByIdAsc();
    */
}