package com.project.bumawiki.domain.image.exception;

import com.project.bumawiki.domain.docs.exception.NoUpdatablePostException;
import com.project.bumawiki.global.error.Exception.BumawikiException;
import com.project.bumawiki.global.error.Exception.ErrorCode;

public class NoImageException extends BumawikiException {

   public static final NoImageException EXCEPTION = new NoImageException(ErrorCode.NO_IMAGE);
    public NoImageException(ErrorCode errorCode) {
        super(errorCode);
    }
}
