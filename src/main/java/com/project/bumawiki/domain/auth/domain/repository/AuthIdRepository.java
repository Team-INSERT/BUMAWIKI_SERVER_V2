package com.project.bumawiki.domain.auth.domain.repository;

import com.project.bumawiki.domain.auth.domain.AuthId;
import org.springframework.data.repository.CrudRepository;

public interface AuthIdRepository extends CrudRepository<AuthId, String> {
    AuthId findByAuthId(String authId);
}
