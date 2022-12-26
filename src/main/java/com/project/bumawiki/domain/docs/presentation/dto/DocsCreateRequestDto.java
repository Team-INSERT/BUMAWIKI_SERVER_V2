package com.project.bumawiki.domain.docs.presentation.dto;

import com.project.bumawiki.domain.docs.domain.type.DocsType;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.List;

@Getter
public class DocsCreateRequestDto {

    private String title;

    private int enroll;

    private String contents;

    private DocsType docsType;

    private List<Clob> image = new ArrayList<>();
}
