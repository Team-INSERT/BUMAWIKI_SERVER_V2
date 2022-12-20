package com.project.bumawiki.global.jwt.exception;

import com.project.bumawiki.global.error.Exception.BumawikiException;
import com.project.bumawiki.global.error.Exception.ErrorCode;

public class InvalidJwtException extends BumawikiException {

    public static InvalidJwtException EXCEPTION = new InvalidJwtException(ErrorCode.INVALID_TOKEN);

    public InvalidJwtException(ErrorCode errorCode) {
        super(errorCode);
    }
}
