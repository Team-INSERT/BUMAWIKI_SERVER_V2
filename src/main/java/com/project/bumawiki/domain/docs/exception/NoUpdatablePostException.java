package com.project.bumawiki.domain.docs.exception;

import com.project.bumawiki.global.error.exception.BumawikiException;
import com.project.bumawiki.global.error.exception.ErrorCode;

public class NoUpdatablePostException extends BumawikiException {

    public static final NoUpdatablePostException EXCEPTION = new NoUpdatablePostException(ErrorCode.NO_UPDATABLE_POST);
    public NoUpdatablePostException(ErrorCode errorCode) {
        super(errorCode);
    }
}
