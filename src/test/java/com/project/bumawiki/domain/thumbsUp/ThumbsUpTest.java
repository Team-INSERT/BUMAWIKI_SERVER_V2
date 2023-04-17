package com.project.bumawiki.domain.thumbsUp;

import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.thumbsUp.domain.ThumbsUp;
import com.project.bumawiki.domain.thumbsUp.domain.ThumbsUps;
import com.project.bumawiki.domain.user.entity.User;
import com.project.bumawiki.global.DataForTest;
import com.project.bumawiki.global.error.exception.BumawikiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ThumbsUpTest {

    //metaData
    private final DataForTest dataForTest = new DataForTest();
    private User user;
    private Docs docs;
    //초기에 객체에 들어갈 Like
    private ThumbsUp userThumbsUp;
    private ThumbsUp docsThumbsUp;
    //삭제하거나 새로 생성할 때 들어갈 Like
    private ThumbsUp userThumbsUpToCompare;
    private ThumbsUp docsThumbsUpToCompare;

    @BeforeEach
    void init() {
        user = dataForTest.getUser();
        docs = dataForTest.getDocs();

        //user, docs에 들어갈 좋아요 생성
        userThumbsUp = ThumbsUp.builder()
                .id(1L)
                .docs(docs)
                .user(user)
                .thumbsUps(user.getThumbsUps())
                .build();

        docsThumbsUp = ThumbsUp.builder()
                .id(2L)
                .docs(docs)
                .user(user)
                .thumbsUps(docs.getThumbsUps())
                .build();

        //Likes에 추가
        user.addThumbsUp(userThumbsUp);
        docs.addThumbsUp(docsThumbsUp);


        userThumbsUpToCompare = ThumbsUp.builder()
                .id(3L)
                .user(user)
                .docs(docs)
                .thumbsUps(user.getThumbsUps())
                .build();

        docsThumbsUpToCompare = ThumbsUp.builder()
                .id(4L)
                .user(user)
                .docs(docs)
                .thumbsUps(docs.getThumbsUps())
                .build();
    }

    @Test
    void 생성() {
        //when
        ThumbsUps userThumbsUps = new ThumbsUps();
        ThumbsUps docsThumbsUps = new ThumbsUps();

        userThumbsUps.addThumbsUp(userThumbsUp);
        docsThumbsUps.addThumbsUp(docsThumbsUp);
        //then
        assertAll(
                () -> assertThat(docs.getThumbsUps().equals(docsThumbsUps))
                        .isEqualTo(true),

                () -> assertThat(user.getThumbsUps().equals(userThumbsUps))
                        .isEqualTo(true));
    }

    @Test
    void 삭제() {
        //when
        docs.cancelThumbsUp(docsThumbsUpToCompare);
        user.thumbsUp(userThumbsUpToCompare);
        //then
        assertAll(
                () -> assertThat(docs.doesUserLike(user))
                        .isFalse(),
                () -> assertThat(user.doYouLike(docs))
                        .isFalse());
    }

    @Test
    void 생성_중복_방지() {
        //when, then
        assertAll(() -> assertThatThrownBy(() -> docs.addThumbsUp(docsThumbsUpToCompare))
                        .isInstanceOf(BumawikiException.class),
                () -> assertThatThrownBy(() -> user.addThumbsUp(userThumbsUpToCompare))
                        .isInstanceOf(BumawikiException.class));
    }

    @Test
    void 삭제_중복_방지() {
        //given
        docs.cancelThumbsUp(docsThumbsUpToCompare);
        user.thumbsUp(userThumbsUpToCompare);
        //when, then
        assertAll(() -> assertThatThrownBy(() -> docs.cancelThumbsUp(docsThumbsUpToCompare))
                        .isInstanceOf(BumawikiException.class),
                () -> assertThatThrownBy(() -> user.thumbsUp(userThumbsUpToCompare))
                        .isInstanceOf(BumawikiException.class));
    }

    @Test
    void 사용자_조회() {
        ThumbsUps thumbsUps = new ThumbsUps();
        thumbsUps.addThumbsUp(userThumbsUp);
        //when,then
        assertThat(user.getThumbsUps().equals(thumbsUps))
                .isEqualTo(true);
    }
}
