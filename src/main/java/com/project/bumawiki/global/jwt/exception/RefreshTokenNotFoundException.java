package com.project.bumawiki.global.jwt.exception;

import com.project.bumawiki.global.error.exception.BumawikiException;
import com.project.bumawiki.global.error.exception.ErrorCode;

public class RefreshTokenNotFoundException extends BumawikiException {

    public final static RefreshTokenNotFoundException EXCEPTION = new RefreshTokenNotFoundException(ErrorCode.REFRESH_TOKEN_NOT_FOUND);

    public RefreshTokenNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
