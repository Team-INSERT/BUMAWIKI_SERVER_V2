package com.project.bumawiki.domain.docs.exception;

import com.project.bumawiki.global.error.Exception.BumawikiException;
import com.project.bumawiki.global.error.Exception.ErrorCode;

public class DocsNotFoundException extends BumawikiException {

    public static final DocsNotFoundException EXCEPTION =new DocsNotFoundException(ErrorCode.DOCS_NOT_FOUND);

    public DocsNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
