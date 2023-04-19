package com.project.bumawiki.domain.docs.domain.repository.wrapper;

import com.project.bumawiki.domain.docs.domain.type.DocsType;

public interface DocsPopularWrapper {

    String getTitle();

    int getEnroll();

    DocsType getDocsType();

    int getThumbsUpCount();
}
