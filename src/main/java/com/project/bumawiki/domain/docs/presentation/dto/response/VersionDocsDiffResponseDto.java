package com.project.bumawiki.domain.docs.presentation.dto.response;

import com.project.bumawiki.domain.docs.domain.type.DocsType;
import com.project.bumawiki.domain.docs.presentation.dto.VersionDocsSummaryDto;
import lombok.Getter;

import java.util.ArrayList;

import static org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch.Diff;

@Getter
public class VersionDocsDiffResponseDto {

    private String title;
    private DocsType docsType;
    private VersionDocsSummaryDto versionDocs;
    private ArrayList<Diff> diff;

    public VersionDocsDiffResponseDto(String title, DocsType docsType, VersionDocsSummaryDto versionDocs, ArrayList<Diff> diff) {
        this.title = title;
        this.docsType = docsType;
        this.versionDocs = versionDocs;
        this.diff = diff;
    }
}
