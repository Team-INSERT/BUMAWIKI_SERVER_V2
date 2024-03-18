package com.project.bumawiki.domain.coin.exception;

import com.project.bumawiki.global.error.exception.BumawikiException;
import com.project.bumawiki.global.error.exception.ErrorCode;

public class AlreadyAwardedTodayException extends BumawikiException {
	public AlreadyAwardedTodayException() {
		super(ErrorCode.ALREADY_AWARDED);
	}
}
