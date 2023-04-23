package com.project.bumawiki.domain.thumbsUp.exception;

import com.project.bumawiki.global.error.exception.BumawikiException;
import com.project.bumawiki.global.error.exception.ErrorCode;

public class YouDontThumbsUpThisDocs extends BumawikiException {

    public static final YouDontThumbsUpThisDocs EXCEPTION = new YouDontThumbsUpThisDocs(ErrorCode.YOU_DONT_THUMBS_UP_THIS_DOCS);
    private YouDontThumbsUpThisDocs(ErrorCode errorCode) {
        super(errorCode);
    }
}
