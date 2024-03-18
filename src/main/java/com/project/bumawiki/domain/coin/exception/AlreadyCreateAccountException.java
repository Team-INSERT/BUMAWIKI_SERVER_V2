package com.project.bumawiki.domain.coin.exception;

import com.project.bumawiki.global.error.exception.BumawikiException;
import com.project.bumawiki.global.error.exception.ErrorCode;

public class AlreadyCreateAccountException extends BumawikiException {
	public AlreadyCreateAccountException() {
		super(ErrorCode.ALREADY_CREATED);
	}
}
