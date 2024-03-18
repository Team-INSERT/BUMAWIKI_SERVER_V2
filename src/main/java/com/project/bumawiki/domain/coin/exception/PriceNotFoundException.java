package com.project.bumawiki.domain.coin.exception;

import com.project.bumawiki.global.error.exception.BumawikiException;
import com.project.bumawiki.global.error.exception.ErrorCode;

public class PriceNotFoundException extends BumawikiException {
	public PriceNotFoundException() {
		super(ErrorCode.PRICE_NOT_FOUND);
	}
}
