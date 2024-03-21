package com.project.bumawiki.domain.docs.exception;

import com.project.bumawiki.global.error.exception.BumawikiException;
import com.project.bumawiki.global.error.exception.ErrorCode;

public class DocsIsNotConflictedException extends BumawikiException {
	public DocsIsNotConflictedException() {
		super(ErrorCode.DOCS_IS_NOT_CONFLICTED);
	}
}
