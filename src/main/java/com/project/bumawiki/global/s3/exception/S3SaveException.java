package com.project.bumawiki.global.s3.exception;

import com.project.bumawiki.global.error.exception.BumawikiException;
import com.project.bumawiki.global.error.exception.ErrorCode;

public class S3SaveException extends BumawikiException {
	public S3SaveException() {
		super(ErrorCode.S3_SAVE_EXCEPTION);
	}
}
