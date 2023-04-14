package com.project.bumawiki.domain.docs;

import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.like.domain.Like;
import com.project.bumawiki.domain.like.domain.Likes;
import com.project.bumawiki.domain.user.entity.User;
import com.project.bumawiki.global.DataForTest;
import com.project.bumawiki.global.error.exception.BumawikiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class DocsLikeTest {

    private final DataForTest dataForTest = new DataForTest();
    private User user;
    private Docs docs;
    private Like like;
    private Likes likes;

    @BeforeEach
    void init() {
        user = dataForTest.getUser();
        docs = dataForTest.getDocs();

        like = Like.builder()
                .id(1L)
                .docs(docs)
                .user(user)
                .likes(new Likes())
                .build();

        user.addLike(like);
        docs.addLike(like);

        likes = new Likes();
        likes.addLike(like);
    }

    @Test
    void 생성() {
        //when, then
        assertAll(
                () -> assertThat(docs.getLikes().equals(likes))
                        .isEqualTo(true),
                () -> assertThat(user.getLikes().equals(likes))
                        .isEqualTo(true));
    }

    @Test
    void 삭제() {
        //when
        docs.cancelLike(like);
        user.cancelLike(like);
        //then
        assertAll(
                () -> assertThat(docs.doesUserLike(user))
                        .isFalse(),
                () -> assertThat(user.doesLikeDocs(docs))
                        .isFalse());
        ;
    }

    @Test
    void 생성_중복_방지() {
        //when, then
        assertAll(() -> assertThatThrownBy(() -> docs.addLike(like))
                        .isInstanceOf(BumawikiException.class),
                () -> assertThatThrownBy(() -> user.addLike(like))
                        .isInstanceOf(BumawikiException.class));
    }

    @Test
    void 삭제_중복_방지() {
        //given
        docs.cancelLike(like);
        user.cancelLike(like);
        //when, then
        assertAll(() -> assertThatThrownBy(() -> docs.cancelLike(like))
                        .isInstanceOf(BumawikiException.class),
                () -> assertThatThrownBy(() -> user.cancelLike(like))
                        .isInstanceOf(BumawikiException.class));
    }

    @Test
    void 사용자_조회() {
        //when,then
        assertThat(user.getLikes().equals(likes))
                .isEqualTo(true);
    }
}
