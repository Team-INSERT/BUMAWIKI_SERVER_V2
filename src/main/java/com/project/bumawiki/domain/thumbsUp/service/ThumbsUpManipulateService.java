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
    public void createDocsThumbsUp(ThumbsUpRequestDto likeRequestDto) {
        User user = getUser();
        checkUserThumbsUpFirst(user);

        Docs foundDocs = getDocs(likeRequestDto);
        checkDocsThumbsUpFirst(foundDocs);

        addDocsThumbsUp(foundDocs, user);
        addUserThumbsUp(foundDocs, user);
    }

    @Transactional
    public void removeLike(ThumbsUpRequestDto likeRequestDto) {
        User user = getUser();
        Docs foundDocs = getDocs(likeRequestDto);

        cancelDocsThumbsUp(foundDocs, user);
        cancelUserThumbsUp(foundDocs, user);
    }

    //처음 좋아요를 누른 건지 확인
    private void checkDocsThumbsUpFirst(Docs docs) {
        if (docs.getThumbsUps() == null) {
            docs.firstThumbsUp(new ThumbsUps());
        }
    }

    //Like 추가
    private void addDocsThumbsUp(Docs foundDocs, User user) {
        foundDocs.addThumbsUp(createDocsThumbsUp(foundDocs, user));
    }

    private void addUserThumbsUp(Docs foundDocs, User user) {
        user.addThumbsUp(createUserThumbsUp(foundDocs, user));
    }

    //Like 삭제
    private void cancelDocsThumbsUp(Docs foundDocs, User user) {
        foundDocs.cancelThumbsUp(createDocsThumbsUp(foundDocs, user));
    }

    private void cancelUserThumbsUp(Docs foundDocs, User user) {
        user.thumbsUp(createUserThumbsUp(foundDocs, user));
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
                .getCurrentUserWithLogin()
                .getId();

        return userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    //Docs, User Like 만들기
    private ThumbsUp createDocsThumbsUp(Docs foundDocs, User user) {
        return ThumbsUp.builder()
                .docs(foundDocs)
                .user(user)
                .thumbsUps(foundDocs.getThumbsUps())
                .build();
    }

    private ThumbsUp createUserThumbsUp(Docs foundDocs, User user) {
        return ThumbsUp.builder()
                .docs(foundDocs)
                .user(user)
                .thumbsUps(user.getThumbsUps())
                .build();
    }
}
