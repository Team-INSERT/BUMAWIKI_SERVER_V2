package com.project.bumawiki.domain.docs.presentation.dto.response;

import com.project.bumawiki.domain.docs.domain.VersionDocs;

public record ContentVersionDocsResponseDto(
	String contents
) {
	public ContentVersionDocsResponseDto(VersionDocs docs) {
		this(docs.getContents());
	}
}
