package com.project.bumawiki.domain.coin.service;

import com.project.bumawiki.global.error.exception.BumawikiException;
import com.project.bumawiki.global.error.exception.ErrorCode;

public class NoPeriodException extends BumawikiException {
	public NoPeriodException() {
		super(ErrorCode.NO_PERIOD);
	}
}
