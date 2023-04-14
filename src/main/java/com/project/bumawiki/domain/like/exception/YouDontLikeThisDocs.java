package com.project.bumawiki.domain.like.exception;

import com.project.bumawiki.global.error.exception.BumawikiException;
import com.project.bumawiki.global.error.exception.ErrorCode;

public class YouDontLikeThisDocs extends BumawikiException {

    public final static YouDontLikeThisDocs EXCEPTION = new YouDontLikeThisDocs(ErrorCode.YOU_DONT_LIKE_THIS_DOCS);
    private YouDontLikeThisDocs(ErrorCode errorCode) {
        super(errorCode);
    }
}
