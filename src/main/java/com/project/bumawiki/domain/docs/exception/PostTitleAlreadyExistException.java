package com.project.bumawiki.domain.docs.exception;

import com.project.bumawiki.global.error.Exception.BumawikiException;
import com.project.bumawiki.global.error.Exception.ErrorCode;

public class PostTitleAlreadyExistException extends BumawikiException {

    public final static PostTitleAlreadyExistException EXCEPTION = new PostTitleAlreadyExistException(ErrorCode.POST_TITLE_ALREADY_EXIST);
    public PostTitleAlreadyExistException(ErrorCode errorCode) {
        super(errorCode);
    }
}
