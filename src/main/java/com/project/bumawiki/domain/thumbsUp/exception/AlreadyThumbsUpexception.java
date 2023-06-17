package com.project.bumawiki.domain.thumbsUp.exception;

import com.project.bumawiki.global.error.exception.BumawikiException;
import com.project.bumawiki.global.error.exception.ErrorCode;

public class AlreadyThumbsUpexception extends BumawikiException {

    public static final AlreadyThumbsUpexception EXCEPTION = new AlreadyThumbsUpexception(ErrorCode.ALREADY_THUMBS_UP);

    private AlreadyThumbsUpexception(ErrorCode errorCode) {
        super(errorCode);
    }
}
