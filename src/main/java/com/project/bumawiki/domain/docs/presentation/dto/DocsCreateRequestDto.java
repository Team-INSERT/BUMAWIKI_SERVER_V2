package com.project.bumawiki.domain.docs.presentation.dto;

import com.project.bumawiki.domain.docs.domain.type.DocsType;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.List;

@Getter
public class DocsCreateRequestDto {

    @Column(length = 32)
    @NotNull
    private String title;

    @Column(length = 8)
    @NotNull
    private int enroll;

    @Column(columnDefinition = "TEXT")
    @NotNull
    private String contents;

    @NotNull
    private DocsType docsType;

    @Lob
    private List<Clob> imageLink = new ArrayList<>();
}
