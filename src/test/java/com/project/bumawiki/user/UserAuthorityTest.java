package com.project.bumawiki.user;

import com.project.bumawiki.domain.user.entity.User;
import com.project.bumawiki.domain.user.entity.authority.Authority;
import com.project.bumawiki.domain.user.entity.repository.UserRepository;
import com.project.bumawiki.domain.user.presentation.dto.UserAuthorityDto;
import com.project.bumawiki.domain.user.service.UserAuthorityService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserAuthorityTest {

    @Mock
    static UserRepository userRepository;
    static User user = User.builder()
            .id(1L)
            .name("홍길동")
            .email("checkbanworkwell@bssm.hs.kr")
            .authority(Authority.USER)
            .enroll(2022)
            .nickName("홍길동전")
            .contributeDocs(null)
            .build();



    @Test
    @Order(1)
    void setUserBanWorkWell(){
        //given
        UserAuthorityDto userAuthorityDto = new UserAuthorityDto(user.getEmail(), Authority.BANNED);
        when(userRepository.findByEmail("checkbanworkwell@bssm.hs.kr")).thenReturn(Optional.ofNullable(user));
        UserAuthorityService userBannedService = new UserAuthorityService(userRepository);
        //when
        Authority authority = userBannedService.execute(userAuthorityDto);
        //then
        Assertions.assertThat(authority).isEqualTo(Authority.BANNED);
    }

    @Test
    @Order(2)
    void setUserUserWell(){
        //given
        when(userRepository.findByEmail("checkbanworkwell@bssm.hs.kr")).thenReturn(Optional.ofNullable(user));
        UserAuthorityDto userAuthorityDto = new UserAuthorityDto(user.getEmail(), Authority.USER);
        UserAuthorityService userBannedService = new UserAuthorityService(userRepository);
        //when
        Authority authority = userBannedService.execute(userAuthorityDto);
        //then
        Assertions.assertThat(authority).isEqualTo(Authority.USER);
    }
    @Test
    @Order(3)
    void setUser(){
        //given
        when(userRepository.findByEmail("checkbanworkwell@bssm.hs.kr")).thenReturn(Optional.ofNullable(user));
        UserAuthorityDto userAuthorityDto = new UserAuthorityDto(user.getEmail(), Authority.ADMIN);
        UserAuthorityService userBannedService = new UserAuthorityService(userRepository);
        //when
        Authority authority = userBannedService.execute(userAuthorityDto);
        //then
        Assertions.assertThat(authority).isEqualTo(Authority.ADMIN);
    }
}
