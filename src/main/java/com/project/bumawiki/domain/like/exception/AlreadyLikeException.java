package com.project.bumawiki.domain.like.exception;

import com.project.bumawiki.global.error.exception.BumawikiException;
import com.project.bumawiki.global.error.exception.ErrorCode;

public class AlreadyLikeException extends BumawikiException {

    public static final AlreadyLikeException EXCEPTION = new AlreadyLikeException(ErrorCode.ALREADY_LIKE);
    private AlreadyLikeException(ErrorCode errorCode) {
        super(errorCode);
    }
}
