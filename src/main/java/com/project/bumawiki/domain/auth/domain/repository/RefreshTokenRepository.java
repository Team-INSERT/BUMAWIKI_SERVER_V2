package com.project.bumawiki.domain.auth.domain.repository;

import com.project.bumawiki.domain.auth.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
    Optional<RefreshToken> findById(String authId);
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
