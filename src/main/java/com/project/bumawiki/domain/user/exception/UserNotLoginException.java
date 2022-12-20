package com.project.bumawiki.domain.user.exception;

import com.project.bumawiki.global.error.Exception.BumawikiException;
import com.project.bumawiki.global.error.Exception.ErrorCode;

public class UserNotLoginException extends BumawikiException {

    public static final UserNotFoundException EXCEPTION = new UserNotFoundException(ErrorCode.USER_NOT_LOGIN);

    public UserNotLoginException(ErrorCode errorCode) {
        super(errorCode);
    }
}
