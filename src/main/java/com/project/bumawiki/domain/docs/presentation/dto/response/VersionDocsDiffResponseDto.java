package com.project.bumawiki.domain.docs.presentation.dto.response;

import lombok.Getter;
import java.util.ArrayList;
import static org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch.*;

@Getter
public class VersionDocsDiffResponseDto {

    private ArrayList<Diff> diff;

    public VersionDocsDiffResponseDto(ArrayList<Diff> diff) {
        this.diff = diff;
    }
}
