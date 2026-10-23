package com.example.next_link.config;

import com.example.next_link.entity.Account;
import com.example.next_link.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class AccountInitializer implements CommandLineRunner {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${ALOY_PASSWORD}")
    private String aloyPassword;

    @Value("${DAVID_PASSWORD}")
    private String davidPassword;

    @Value("${RAPHAEL_PASSWORD}")
    private String raphaelPassword;


    @Override
    public void run(String... args) {
        createIfAbsent("david", davidPassword);
        createIfAbsent("aloy", aloyPassword);
        createIfAbsent("raphael", raphaelPassword);

    }

    private void createIfAbsent(String username, String rawPassword) {


        Optional<Account> account = accountRepository.findByUsername(username);

        if (account.isEmpty()){

            Account newAccount = new Account();
            newAccount.setUsername(username);
            newAccount.setPasswordHash(passwordEncoder.encode(rawPassword));

            accountRepository.save(newAccount);
        }
    }

}
