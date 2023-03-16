package com.project.bumawiki.domain.user.presentation;

import com.project.bumawiki.domain.user.entity.authority.Authority;
import com.project.bumawiki.domain.user.presentation.dto.UserAuthorityDto;
import com.project.bumawiki.domain.user.service.UserAuthorityService;
import com.project.bumawiki.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserAuthorityController {
    private final UserAuthorityService userAuthorityService;
    private final UserService userService;

    @PutMapping("/set/authority")
    public Authority setUserAuthority(@RequestBody final UserAuthorityDto userAuthorityDto){
        Authority execute = userAuthorityService.execute(userAuthorityDto);
        return execute;
    }
}
