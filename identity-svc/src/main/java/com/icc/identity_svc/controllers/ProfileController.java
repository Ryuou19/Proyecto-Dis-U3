package com.icc.identity_svc.controllers;

import com.icc.identity_svc.models.Profile;
import com.icc.identity_svc.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @CrossOrigin(origins = "*")
    @GetMapping("")
    public ResponseEntity<Profile> getProfile(@RequestParam("accountId") Long accountId) {
        Optional<Profile> profileOpt = profileService.getProfileByAccountId(accountId);
        if (profileOpt.isPresent()) {
            return ResponseEntity.ok(profileOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

