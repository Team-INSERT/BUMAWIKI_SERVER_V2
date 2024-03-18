package com.project.bumawiki.domain.coin.exception;

import com.project.bumawiki.global.error.exception.BumawikiException;
import com.project.bumawiki.global.error.exception.ErrorCode;

public class CoinAccountNotFoundException extends BumawikiException {
	public CoinAccountNotFoundException() {
		super(ErrorCode.COIN_ACCOUNT_NOT_FOUND_EXCEPTION);
	}
}
