package com.project.bumawiki.domain.coin.exception;

import com.project.bumawiki.global.error.exception.BumawikiException;
import com.project.bumawiki.global.error.exception.ErrorCode;

public class TradeAlreadyFinishedException extends BumawikiException {
	public TradeAlreadyFinishedException() {
		super(ErrorCode.TRADE_ALREADY_FINISHED);
	}
}
