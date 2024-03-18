package com.project.bumawiki.domain.coin.domain;

import com.project.bumawiki.domain.coin.domain.type.TradeStatus;

public record TradeWithoutTradeStatusAndCoinAccountId(
	Long coinPrice,
	Long coinCount
) {
	public Trade toTrade(CoinAccount coinAccount) {
		return new Trade(
			coinPrice,
			coinCount,
			coinPrice * coinCount,
			TradeStatus.NONE,
			coinAccount.getId()
		);
	}
}
