package com.project.bumawiki.domain.thumbsUp;

import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.thumbsUp.domain.ThumbsUp;
import com.project.bumawiki.domain.thumbsUp.domain.collection.DocsThumbsUps;
import com.project.bumawiki.domain.thumbsUp.domain.collection.UserThumbsUps;
import com.project.bumawiki.domain.user.domain.User;
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
    private ThumbsUp thumbsUp;
    //삭제하거나 새로 생성할 때 들어갈 Like
    private ThumbsUp thumbsUpToCompare;

    @BeforeEach
    void init() {
        user = dataForTest.getUser();
        docs = dataForTest.getDocs();

        //user, docs에 들어갈 좋아요 생성
        thumbsUp = ThumbsUp.builder()
                .id(1L)
                .docs(docs)
                .user(user)
                .build();

        //Likes에 추가
        user.addThumbsUp(thumbsUp);
        docs.addThumbsUp(thumbsUp);


        thumbsUpToCompare = ThumbsUp.builder()
                .id(3L)
                .user(user)
                .docs(docs)
                .build();
    }

    @Test
    void 생성() {
        //when
        UserThumbsUps userThumbsUps = new UserThumbsUps();
        DocsThumbsUps docsThumbsUps = new DocsThumbsUps();

        userThumbsUps.addThumbsUp(thumbsUp);
        docsThumbsUps.addThumbsUp(thumbsUp);
        //then
        assertAll(
                () -> assertThat(docs.getDocsThumbsUp().getThumbsUps().equals(userThumbsUps.getThumbsUps()))
                        .isEqualTo(true),

                () -> assertThat(user.getUserThumbsUps().getThumbsUps().equals(docsThumbsUps.getThumbsUps()))
                        .isEqualTo(true));
    }

    @Test
    void 삭제() {
        //when
        docs.cancelThumbsUp(thumbsUpToCompare);
        user.cancelThumbsUp(thumbsUpToCompare);
        //then
        assertAll(
                () -> assertThat(docs.doesUserThumbsUp(user))
                        .isFalse(),
                () -> assertThat(user.doYouLike(docs))
                        .isFalse());
    }

    @Test
    void 생성_중복_방지() {
        //when, then
        assertAll(() -> assertThatThrownBy(() -> docs.addThumbsUp(thumbsUpToCompare))
                        .isInstanceOf(BumawikiException.class),
                () -> assertThatThrownBy(() -> user.addThumbsUp(thumbsUpToCompare))
                        .isInstanceOf(BumawikiException.class));
    }

    @Test
    void 삭제_중복_방지() {
        //given
        docs.cancelThumbsUp(thumbsUpToCompare);
        user.cancelThumbsUp(thumbsUpToCompare);
        //when, then
        assertAll(() -> assertThatThrownBy(() -> docs.cancelThumbsUp(thumbsUpToCompare))
                        .isInstanceOf(BumawikiException.class),
                () -> assertThatThrownBy(() -> user.cancelThumbsUp(thumbsUpToCompare))
                        .isInstanceOf(BumawikiException.class));
    }

    @Test
    void 사용자_조회() {
        UserThumbsUps userThumbsUps = new UserThumbsUps();
        userThumbsUps.addThumbsUp(thumbsUp);
        //when,then
        assertThat(user.getUserThumbsUps().getThumbsUps().equals(userThumbsUps.getThumbsUps()))
                .isEqualTo(true);
    }
}
