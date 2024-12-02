package com.icc.identity_svc.services;

import com.icc.identity_svc.models.Account;
import com.icc.identity_svc.models.Profile;
import com.icc.identity_svc.repositories.AccountRepository;
import com.icc.identity_svc.repositories.ProfileRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public Account register(String email, String password, String name, String phone, String street, String city) {
        Account account = new Account();
        account.setEmail(email);
        account.setPassword(passwordEncoder.encode(password));
        account = accountRepository.save(account);

        Profile profile = new Profile();
        profile.setAccount(account);
        profile.setName(name);
        profile.setPhone(phone);
        profile.setStreet(street);
        profile.setCity(city);
        profileRepository.save(profile);

        return account;
    }

    public Optional<Account> login(String email, String password) {
        Optional<Account> accountOpt = accountRepository.findByEmail(email);
        if (accountOpt.isPresent() && passwordEncoder.matches(password, accountOpt.get().getPassword())) {
            return accountOpt;
        }
        return Optional.empty();
    }
}
