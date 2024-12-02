package com.icc.identity_svc.configs;

import com.icc.identity_svc.models.Account;
import com.icc.identity_svc.models.Profile;
import com.icc.identity_svc.repositories.AccountRepository;
import com.icc.identity_svc.repositories.ProfileRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (accountRepository.count() == 0 && profileRepository.count() == 0) {
            seedDatabase();
        }
    }

    private void seedDatabase() {
        Account account1 = new Account();
        account1.setEmail("user1@example.com");
        account1.setPassword(passwordEncoder.encode("password1"));

        Account account2 = new Account();
        account2.setEmail("user2@example.com");
        account2.setPassword(passwordEncoder.encode("password2"));

        Account account3 = new Account();
        account3.setEmail("user3@example.com");
        account3.setPassword(passwordEncoder.encode("password3"));

        accountRepository.saveAll(Arrays.asList(account1, account2, account3));

        Profile profile1 = new Profile();
        profile1.setAccount(account1);
        profile1.setName("User One");
        profile1.setPhone("123-456-7890");
        profile1.setStreet("123 Street");
        profile1.setCity("Santiago");

        Profile profile2 = new Profile();
        profile2.setAccount(account2);
        profile2.setName("User Two");
        profile2.setPhone("098-765-4321");
        profile2.setStreet("456 Avenue");
        profile2.setCity("Valparaiso");

        Profile profile3 = new Profile();
        profile3.setAccount(account3);
        profile3.setName("User Three");
        profile3.setPhone("555-555-5555");
        profile3.setStreet("789 Boulevard");
        profile3.setCity("Concepcion");

        profileRepository.saveAll(Arrays.asList(profile1, profile2, profile3));
    }

}
