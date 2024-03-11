package com.project.bumawiki.global.s3.exception;

import com.project.bumawiki.global.error.exception.BumawikiException;
import com.project.bumawiki.global.error.exception.ErrorCode;

public class ImageNotFoundException extends BumawikiException {
	public ImageNotFoundException() {
		super(ErrorCode.IMAGE_NOT_FOUND_EXCEPTION);
	}
}
