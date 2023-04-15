package com.project.bumawiki.domain.like.exception;

import com.project.bumawiki.global.error.exception.BumawikiException;
import com.project.bumawiki.global.error.exception.ErrorCode;

public class NoDocsYouLikeException extends BumawikiException {

    public static NoDocsYouLikeException EXCEPTION = new NoDocsYouLikeException(ErrorCode.NO_DOCS_YOU_LIKE);
    private NoDocsYouLikeException(ErrorCode errorCode) {
        super(errorCode);
    }
}
