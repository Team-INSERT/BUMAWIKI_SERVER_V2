package com.project.bumawiki.domain.coin.exception;

import com.project.bumawiki.global.error.exception.BumawikiException;
import com.project.bumawiki.global.error.exception.ErrorCode;

public class RandomInstanceException extends BumawikiException {
	public RandomInstanceException() {
		super(ErrorCode.RANDOM_INSTANCE);
	}
}
