package com.project.bumawiki.domain.docs.exception;

import com.project.bumawiki.global.error.exception.BumawikiException;
import com.project.bumawiki.global.error.exception.ErrorCode;

public class DocsConflictedException extends BumawikiException {
	public DocsConflictedException() {
		super(ErrorCode.DOCS_CONFLICTED);
	}
}
