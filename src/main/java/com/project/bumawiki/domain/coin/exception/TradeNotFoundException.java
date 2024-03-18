package com.project.bumawiki.domain.coin.exception;

import com.project.bumawiki.global.error.exception.BumawikiException;
import com.project.bumawiki.global.error.exception.ErrorCode;

public class TradeNotFoundException extends BumawikiException {
	public TradeNotFoundException() {
		super(ErrorCode.TRADE_NOT_FOUND);
	}
}
