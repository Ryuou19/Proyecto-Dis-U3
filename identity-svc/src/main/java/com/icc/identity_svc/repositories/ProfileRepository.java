package com.icc.identity_svc.repositories;

import com.icc.identity_svc.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByAccountId(Long accountId);
}