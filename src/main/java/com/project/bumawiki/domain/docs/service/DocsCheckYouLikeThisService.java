package com.project.bumawiki.domain.docs.service;

import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.docs.facade.DocsFacade;
import com.project.bumawiki.domain.user.UserFacade;
import com.project.bumawiki.domain.user.entity.User;
import com.project.bumawiki.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DocsCheckYouLikeThisService {
    private final DocsFacade docsFacade;
    private final UserFacade userFacade;

    public boolean checkUserLikeThisDocs(final Long docsId) {
        User currentUser = userFacade.getCurrentUser();
        Docs docs = docsFacade.findById(docsId, ErrorCode.DOCS_NOT_FOUND);
        User foundUser = userFacade.getUserByEmail(currentUser.getEmail());

        return docs.doesUserLike(foundUser);
    }
}
