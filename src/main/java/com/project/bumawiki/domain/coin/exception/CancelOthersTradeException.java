package com.project.bumawiki.domain.coin.exception;

import com.project.bumawiki.global.error.exception.BumawikiException;
import com.project.bumawiki.global.error.exception.ErrorCode;

public class CancelOthersTradeException extends BumawikiException {
	public CancelOthersTradeException() {
		super(ErrorCode.CANCEL_OTHERS_TRADE);
	}
}
