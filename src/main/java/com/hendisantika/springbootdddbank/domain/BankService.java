package com.hendisantika.springbootdddbank.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
