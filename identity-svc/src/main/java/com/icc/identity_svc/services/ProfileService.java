package com.icc.identity_svc.services;

import com.icc.identity_svc.models.Profile;
import com.icc.identity_svc.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public Optional<Profile> getProfileByAccountId(Long accountId) {
        return profileRepository.findByAccountId(accountId);
    }
}
