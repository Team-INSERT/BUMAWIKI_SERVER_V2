package com.project.bumawiki.domain.docs.presentation.dto;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.List;

@Getter
public class DocsUpdateRequestDto {

    @NotNull
    private String title;

    @NotNull
    private int enroll;

    @NotNull
    private String contents;

    @Lob
    private List<Clob> imageLink = new ArrayList<>();
}
