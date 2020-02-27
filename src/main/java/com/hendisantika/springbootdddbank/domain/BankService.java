package com.hendisantika.springbootdddbank.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-ddd-bank
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 27/02/20
 * Time: 07.02
 */
@Service
public class BankService {

    // Required repositories as by Ports and Adapters Pattern:
    //See http://www.dossier-andreas.net/software_architecture/ports_and_adapters.html
    private final ClientRepository clientRepository;
    private final AccountAccessRepository accountAccessRepository;

    @Autowired
    public BankService(final ClientRepository clientRepository, final AccountAccessRepository accountAccessRepository) {
        this.clientRepository = clientRepository;
        this.accountAccessRepository = accountAccessRepository;
    }

    /**
     * Command: Creates a new bank {@link Client} with given username and birthDate
     * and saves it giving it a unique ID.
     *
     * @param username  the unique username of the new client. It must match the regular
     *                  expression <code>[a-z_A-Z][a-z_A-Z0-9]{0,30}</code>.
     * @param birthDate the birth date of the new client, must not be null
     * @return the saved new {@link Client} with the ID set.
     * @throws UsernameExc the username does not match the required pattern.
     */
    public Client createClient(final String username, final LocalDate birthDate) {
        final Pattern pattern = Pattern.compile("[a-z_A-Z][a-z_A-Z0-9]{0,30}");
        if (!pattern.matcher(username).matches()) {
            throw create(UsernameExc.class, username);
        }
        final Client client = clientRepository.save(new Client(username, birthDate));
        return client;
    }

    /**
     * Illegal username "{0}". Must have 1..31 characters, start with a letter and
     * contain only english letters, underscores, and decimal digits.
     */
    @SuppressWarnings("serial")
    public static class UsernameExc extends multex.Exc {
    }

    /**
     * Command: Deletes the given {@link Client}. The {@link Client} looses all
     * manager account accesses to accounts, where he was manager.
     *
     * @param client the {@link Client} to be deleted
     * @throws DeleteExc Client has accounts, where he is the owner. So he cannot yet be
     *                   deleted.
     */
    public void deleteClient(final Client client) {
        final List<AccountAccess> managedAccounts = accountAccessRepository.findManagedAccountsOf(client, true);
        for (final AccountAccess accountAccess : managedAccounts) {
            if (accountAccess.isOwner()) {
                throw create(DeleteExc.class, client, accountAccess.getAccount());
            } else {
                accountAccessRepository.delete(accountAccess);
            }
        }
        clientRepository.delete(client);
    }
}
