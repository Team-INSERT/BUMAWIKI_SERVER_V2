package com.project.bumawiki.domain.docs.exception;

import com.project.bumawiki.global.error.exception.BumawikiException;
import com.project.bumawiki.global.error.exception.ErrorCode;

public class NoSuchVersionException extends BumawikiException {
	public NoSuchVersionException() {
		super(ErrorCode.NO_SUCH_VERSION);
	}
}
