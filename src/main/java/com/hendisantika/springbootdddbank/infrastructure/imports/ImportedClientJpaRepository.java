package com.hendisantika.springbootdddbank.infrastructure.imports;

import com.hendisantika.springbootdddbank.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-ddd-bank
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 01/03/20
 * Time: 07.02
 */
public interface ImportedClientJpaRepository extends JpaRepository<Client, Long> {

    /**
     * Deletes all Clients. Useful for test scenarios in order to start with an empty client set
     */
    void deleteAll();

    Client save(Client client);

    void delete(Client client);

    Optional<Client> findOneById(Long id);

    Optional<Client> findOneByUsername(String username);

    Optional<Client> findOneByUsernameAndBirthDate(String name, LocalDate birthDate);

    List<Client> findAllByOrderByIdDesc();

    List<Client> findAllByBirthDateGreaterThanEqualOrderByBirthDateDescIdDesc(LocalDate minDate);

    Optional<Client> findFirstByOrderByIdAsc();
}