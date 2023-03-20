package com.project.bumawiki.domain.docs.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum DocsType {
    STUDENT("student"),
    ACCIDENT("accident"),
    TEACHER("teacher"),
    MAJOR_TEACHER("major_teacher"),
    MENTOR_TEACHER("mentor_teacher"),
    CLUB("club"),
    FREE_CLUB("free_club"),
    FRAME("frame"),
    NOTICE("notice"),
    ReadOnly("readonly");

    private final String name;

    private static final Map<String, DocsType> BY_LABEL =
            Stream.of(values()).collect(Collectors.toMap(DocsType::getName, e -> e));

    public static DocsType valueOfLabel(String docsType) {
        return BY_LABEL.get(docsType);
    }
}
