package com.project.bumawiki.domain.coin.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.bumawiki.domain.coin.domain.CoinAccount;
import com.project.bumawiki.domain.coin.exception.CoinAccountNotFoundException;

public interface CoinAccountRepository extends JpaRepository<CoinAccount, Long> {
	Optional<CoinAccount> findByUserId(Long userId);

	boolean existsByUserId(Long userId);

	default CoinAccount getByUserId(Long userId) {
		return findByUserId(userId)
			.orElseThrow(CoinAccountNotFoundException::new);
	}

	default CoinAccount getById(Long id) {
		return findById(id)
			.orElseThrow(CoinAccountNotFoundException::new);
	}

}
