package com.project.bumawiki.domain.user;

import com.project.bumawiki.domain.user.domain.User;
import com.project.bumawiki.domain.user.domain.authority.Authority;
import com.project.bumawiki.domain.user.domain.repository.UserRepositoryMapper;
import com.project.bumawiki.domain.user.presentation.dto.UserAuthorityDto;
import com.project.bumawiki.domain.user.service.UserAuthorityService;
import com.project.bumawiki.global.DataForTest;
import org.assertj.core.api.Assertions;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserAuthorityTest {

    static User user;
    private final DataForTest dataForTest = new DataForTest();
    @Mock
    private UserRepositoryMapper userRepositoryMapper;

    private UserAuthorityService userBannedService;

    @NotNull
    private static UserAuthorityDto getUserAuthorityDto(Authority authority) {
        return new UserAuthorityDto(user.getEmail(), authority);
    }

    @BeforeEach
    void init() {
        user = dataForTest.getUser();
        //유저 밴 서비스 생성
        userBannedService = new UserAuthorityService(userRepositoryMapper);
        //이 이메일로 조회시 user 반환
        when(userRepositoryMapper
                .getByEmail("checkbanworkwell@bssm.hs.kr"))
                .thenReturn(user);
    }

    @Test
    void 유저를_밴으로() {
        //given
        UserAuthorityDto userAuthorityDto = getUserAuthorityDto(Authority.BANNED);
        //when
        Authority authority = userBannedService.execute(userAuthorityDto);
        //then
        Assertions.assertThat(authority).isEqualTo(Authority.BANNED);
    }

    @Test
    void 유저를_유저로() {
        //given
        UserAuthorityDto userAuthorityDto = getUserAuthorityDto(Authority.USER);
        //when
        Authority authority = userBannedService.execute(userAuthorityDto);
        //then
        Assertions.assertThat(authority).isEqualTo(Authority.USER);
    }

    @Test
    void 유저를_어드민으로() {
        //given
        UserAuthorityDto userAuthorityDto = getUserAuthorityDto(Authority.ADMIN);
        //when
        Authority authority = userBannedService.execute(userAuthorityDto);
        //then
        Assertions.assertThat(authority).isEqualTo(Authority.ADMIN);
    }
}
