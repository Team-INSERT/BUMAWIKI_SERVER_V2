package com.project.bumawiki.domain.docs.presentation.dto;

import lombok.Getter;
import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch;

import java.util.LinkedList;

@Getter
public class VersionDocsDiffResponseDto {

    LinkedList<DiffMatchPatch.Diff> diff;

    public VersionDocsDiffResponseDto(LinkedList<DiffMatchPatch.Diff> diff) {
        this.diff = diff;
    }
}
