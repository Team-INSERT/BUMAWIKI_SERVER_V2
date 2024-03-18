package com.project.bumawiki.domain.coin.exception;

import com.project.bumawiki.global.error.exception.BumawikiException;
import com.project.bumawiki.global.error.exception.ErrorCode;

public class CoinNotEnoughException extends BumawikiException {
	public CoinNotEnoughException() {
		super(ErrorCode.COIN_NOT_ENOUGH);
	}
}
