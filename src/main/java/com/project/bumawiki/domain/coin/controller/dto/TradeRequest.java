package com.project.bumawiki.domain.coin.controller.dto;

import com.project.bumawiki.domain.coin.domain.Trade;
import com.project.bumawiki.domain.coin.domain.TradeWithoutTradeStatusAndCoinAccountId;
import com.project.bumawiki.domain.coin.domain.type.TradeStatus;

public record TradeRequest(
	Long coinPrice,
	Long coinCount
) {
	public TradeWithoutTradeStatusAndCoinAccountId toEntity() {
		return new TradeWithoutTradeStatusAndCoinAccountId(
			coinPrice,
			coinCount
		);
	}
}
