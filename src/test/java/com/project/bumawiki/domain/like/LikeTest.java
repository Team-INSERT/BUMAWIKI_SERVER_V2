package com.project.bumawiki.domain.like;

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

public class LikeTest {

    //metaData
    private final DataForTest dataForTest = new DataForTest();
    private User user;
    private Docs docs;
    //초기에 객체에 들어갈 Like
    private Like userLike;
    private Like docsLike;
    //삭제하거나 새로 생성할 때 들어갈 Like
    private Like userLikeToCompare;
    private Like docsLikeToCompare;

    @BeforeEach
    void init() {
        user = dataForTest.getUser();
        docs = dataForTest.getDocs();

        //user, docs에 들어갈 좋아요 생성
        userLike = Like.builder()
                .id(1L)
                .docs(docs)
                .user(user)
                .likes(user.getLikes())
                .build();

        docsLike = Like.builder()
                .id(2L)
                .docs(docs)
                .user(user)
                .likes(docs.getLikes())
                .build();

        //Likes에 추가
        user.addLike(userLike);
        docs.addLike(docsLike);


        userLikeToCompare = Like.builder()
                .id(3L)
                .user(user)
                .docs(docs)
                .likes(user.getLikes())
                .build();

        docsLikeToCompare = Like.builder()
                .id(4L)
                .user(user)
                .docs(docs)
                .likes(docs.getLikes())
                .build();
    }

    @Test
    void 생성() {
        //when
        Likes userLikes = new Likes();
        Likes docsLikes = new Likes();

        userLikes.addLike(userLike);
        docsLikes.addLike(docsLike);
        //then
        assertAll(
                () -> assertThat(docs.getLikes().equals(docsLikes))
                        .isEqualTo(true),

                () -> assertThat(user.getLikes().equals(userLikes))
                        .isEqualTo(true));
    }

    @Test
    void 삭제() {
        //when
        docs.cancelLike(docsLikeToCompare);
        user.cancelLike(userLikeToCompare);
        //then
        assertAll(
                () -> assertThat(docs.doesUserLike(user))
                        .isFalse(),
                () -> assertThat(user.doesLikeDocs(docs))
                        .isFalse());
    }

    @Test
    void 생성_중복_방지() {
        //when, then
        assertAll(() -> assertThatThrownBy(() -> docs.addLike(docsLikeToCompare))
                        .isInstanceOf(BumawikiException.class),
                () -> assertThatThrownBy(() -> user.addLike(userLikeToCompare))
                        .isInstanceOf(BumawikiException.class));
    }

    @Test
    void 삭제_중복_방지() {
        //given
        docs.cancelLike(docsLikeToCompare);
        user.cancelLike(userLikeToCompare);
        //when, then
        assertAll(() -> assertThatThrownBy(() -> docs.cancelLike(docsLikeToCompare))
                        .isInstanceOf(BumawikiException.class),
                () -> assertThatThrownBy(() -> user.cancelLike(userLikeToCompare))
                        .isInstanceOf(BumawikiException.class));
    }

    @Test
    void 사용자_조회() {
        Likes likes = new Likes();
        likes.addLike(userLike);
        //when,then
        assertThat(user.getLikes().equals(likes))
                .isEqualTo(true);
    }
}
