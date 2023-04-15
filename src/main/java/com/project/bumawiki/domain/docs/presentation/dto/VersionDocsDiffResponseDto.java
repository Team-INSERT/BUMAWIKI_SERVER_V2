package com.project.bumawiki.domain.docs.presentation.dto;

import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.docs.domain.VersionDocs;
import com.project.bumawiki.domain.docs.domain.type.DocsType;
import lombok.Getter;
import java.util.ArrayList;
import static org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch.*;

@Getter
public class VersionDocsDiffResponseDto {

    private String title;
    private DocsType docsType;
    private VersionDocs versionDocs;
    private ArrayList<Diff> diff;

    public VersionDocsDiffResponseDto(String title, DocsType docsType, VersionDocs versionDocs, ArrayList<Diff> diff) {
        this.title = title;
        this.docsType = docsType;
        this.versionDocs = versionDocs;
        this.diff = diff;
    }
}
