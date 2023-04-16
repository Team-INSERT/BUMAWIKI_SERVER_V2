package com.project.bumawiki.domain.thumbsUp.service;

import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.docs.facade.DocsFacade;
import com.project.bumawiki.domain.thumbsUp.domain.ThumbsUp;
import com.project.bumawiki.domain.thumbsUp.domain.ThumbsUps;
import com.project.bumawiki.domain.thumbsUp.presentation.dto.ThumbsUpRequestDto;
import com.project.bumawiki.domain.user.entity.User;
import com.project.bumawiki.domain.user.entity.repository.UserRepository;
import com.project.bumawiki.domain.user.exception.UserNotFoundException;
import com.project.bumawiki.global.error.exception.ErrorCode;
import com.project.bumawiki.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ThumbsUpManipulateService {
    private final DocsFacade docsFacade;
    private final UserRepository userRepository;

    private static void checkUserThumbsUpFirst(User user) {
        if (user.getThumbsUps() == null) {
            user.firstThumbsUp(new ThumbsUps());
        }
    }

    @Transactional
    public void createDocsLike(ThumbsUpRequestDto likeRequestDto) {
        User user = getUser();
        checkUserThumbsUpFirst(user);

        Docs foundDocs = getDocs(likeRequestDto);
        checkDocsThumbsUpFirst(foundDocs);


        addDocsLike(foundDocs, user);
        addUserLike(foundDocs, user);
    }

    @Transactional
    public void removeLike(ThumbsUpRequestDto likeRequestDto) {
        User user = getUser();
        Docs foundDocs = getDocs(likeRequestDto);

        cancelDocsLike(foundDocs, user);
        cancelUserLike(foundDocs, user);
    }

    //처음 좋아요를 누른 건지 확인
    private void checkDocsThumbsUpFirst(Docs docs) {
        if (docs.getThumbsUps() == null) {
            docs.firstThumbsUp(new ThumbsUps());
        }
    }

    //Like 추가
    private void addDocsLike(Docs foundDocs, User user) {
        foundDocs.addThumbsUp(createDocsLike(foundDocs, user));
    }

    private void addUserLike(Docs foundDocs, User user) {
        user.addThumbsUp(createUserLike(foundDocs, user));
    }

    //Like 삭제
    private void cancelDocsLike(Docs foundDocs, User user) {
        foundDocs.cancelLike(createDocsLike(foundDocs, user));
    }

    private void cancelUserLike(Docs foundDocs, User user) {
        user.cancelLike(createUserLike(foundDocs, user));
    }

    //Docs, User 가져오기
    private Docs getDocs(ThumbsUpRequestDto likeRequestDto) {
        return docsFacade.findById(
                likeRequestDto.getDocsId(),
                ErrorCode.NO_DOCS_YOU_THUMBS_UP
        );
    }

    private User getUser() {
        Long userId = SecurityUtil
                .getCurrentUser()
                .getUser()
                .getId();

        return userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    //Docs, User Like 만들기
    private ThumbsUp createDocsLike(Docs foundDocs, User user) {
        return ThumbsUp.builder()
                .docs(foundDocs)
                .user(user)
                .thumbsUps(foundDocs.getThumbsUps())
                .build();
    }

    private ThumbsUp createUserLike(Docs foundDocs, User user) {
        return ThumbsUp.builder()
                .docs(foundDocs)
                .user(user)
                .thumbsUps(user.getThumbsUps())
                .build();
    }
}
