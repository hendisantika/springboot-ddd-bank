package com.hendisantika.springbootdddbank.infrastructure.imports;

import com.hendisantika.springbootdddbank.domain.Account;
import com.hendisantika.springbootdddbank.domain.AccountAccess;
import com.hendisantika.springbootdddbank.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImportedAccountAccessJpaRepository extends JpaRepository<AccountAccess, Long> {

    void deleteAll();

    AccountAccess save(AccountAccess account);

    void delete(AccountAccess account);

    List<AccountAccess> findAllByClientAndIsOwnerGreaterThanEqualOrderByIdDesc(Client client, boolean asOwner);

    Optional<AccountAccess> findOneByClientAndAccount(Client client, Account account);

    //Optional<AccountAccess> findOneByClientAndAccount(Client client, Long accountId);

    List<AccountAccess> findAllByAccountBalanceCentsGreaterThanEqualOrderByAccountBalanceCentsDescClientIdDesc(
            long minCents);

}