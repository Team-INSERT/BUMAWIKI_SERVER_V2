package com.project.bumawiki.domain.coin.controller.dto;

import java.time.LocalDateTime;

import com.project.bumawiki.domain.coin.domain.CoinAccount;

public record CoinAccountResponse(
	Long userId,
	Long money,
	Long coin,
	LocalDateTime lastRewardedTime
) {

	public static CoinAccountResponse from(CoinAccount coinAccount) {
		return new CoinAccountResponse(
			coinAccount.getUserId(),
			coinAccount.getMoney(),
			coinAccount.getCoin(),
			coinAccount.getLastRewardedTime()
		);
	}
}
