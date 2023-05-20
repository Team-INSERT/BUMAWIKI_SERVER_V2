package com.project.bumawiki.domain.docs.domain.repository;

import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.docs.presentation.dto.response.VersionResponseDto;

public interface CustomDocsRepository {

    VersionResponseDto getDocsVersion(Docs docs);
}
