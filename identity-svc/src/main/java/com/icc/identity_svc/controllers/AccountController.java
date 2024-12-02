package com.icc.identity_svc.controllers;

import com.icc.identity_svc.domain.AccountProfileDTO;
import com.icc.identity_svc.models.Account;
import com.icc.identity_svc.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @CrossOrigin(origins = "*")
    @PostMapping("/register")
    public ResponseEntity<Account> register(@RequestBody AccountProfileDTO accountProfileDTO) {
        Account savedAccount = accountService.register(
                accountProfileDTO.getEmail(),
                accountProfileDTO.getPassword(),
                accountProfileDTO.getName(),
                accountProfileDTO.getPhone(),
                accountProfileDTO.getStreet(),
                accountProfileDTO.getCity()

        );
        return ResponseEntity.ok(savedAccount);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public ResponseEntity<Map<String, Long>> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");
        Optional<Account> accountOpt = accountService.login(email, password);
        if (accountOpt.isPresent()) {
            Map<String, Long> response = new HashMap<>();
            response.put("accountId", accountOpt.get().getId());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}

