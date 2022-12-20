package com.project.bumawiki.domain.auth.domain.repository;

import com.project.bumawiki.domain.auth.domain.AuthId;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthIdRepository extends CrudRepository<AuthId, String> {
    Optional<AuthId> findByAuthId(String authId);
}
