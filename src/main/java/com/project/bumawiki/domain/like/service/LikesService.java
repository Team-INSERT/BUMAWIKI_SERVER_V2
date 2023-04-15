package com.project.bumawiki.domain.like.service;

import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.docs.facade.DocsFacade;
import com.project.bumawiki.domain.like.domain.Like;
import com.project.bumawiki.domain.like.presentation.dto.LikeRequestDto;
import com.project.bumawiki.domain.user.entity.User;
import com.project.bumawiki.global.error.exception.ErrorCode;
import com.project.bumawiki.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class LikesService {
    private final DocsFacade docsFacade;

    @Transactional
    public void createDocsLike(LikeRequestDto likeRequestDto) {
        Docs foundDocs = getDocs(likeRequestDto);
        User user = getUser();

        addDocsLike(foundDocs, user);
        addUserLike(foundDocs, user);
    }

    @Transactional
    public void removeLike(LikeRequestDto likeRequestDto) {
        Docs foundDocs = getDocs(likeRequestDto);
        User user = getUser();

        cancelDocsLike(foundDocs, user);
        cancelUserLike(foundDocs, user);
    }

    //Like 추가
    private void addDocsLike(Docs foundDocs, User user) {
        foundDocs.addLike(createDocsLike(foundDocs, user));
    }
    private void addUserLike(Docs foundDocs, User user) {
        user.addLike(createUserLike(foundDocs, user));
    }

    //Like 삭제
    private void cancelDocsLike(Docs foundDocs, User user) {
        foundDocs.cancelLike(createDocsLike(foundDocs, user));
    }

    private void cancelUserLike(Docs foundDocs, User user) {
        user.cancelLike(createUserLike(foundDocs, user));
    }

    //Docs, User 가져오기
    private Docs getDocs(LikeRequestDto likeRequestDto) {
        return docsFacade.findById(
                likeRequestDto.getDocsId(),
                ErrorCode.NO_DOCS_YOU_LIKE
        );
    }

    private static User getUser() {
        return SecurityUtil
                .getCurrentUser()
                .getUser();
    }

    //Docs, User Like 만들기
    private Like createDocsLike(Docs foundDocs, User user) {
        return Like.builder()
                .docs(foundDocs)
                .user(user)
                .likes(foundDocs.getLikes())
                .build();
    }

    private Like createUserLike(Docs foundDocs, User user) {
        return Like.builder()
                .docs(foundDocs)
                .user(user)
                .likes(user.getLikes())
                .build();
    }
}
