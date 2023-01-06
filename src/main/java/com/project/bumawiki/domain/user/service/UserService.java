package com.project.bumawiki.domain.user.service;

import com.project.bumawiki.domain.auth.domain.repository.AuthIdRepository;
import com.project.bumawiki.domain.user.exception.UserNotLoginException;
import com.project.bumawiki.global.annotation.ServiceWithTransactionalReadOnly;
import com.project.bumawiki.global.jwt.config.JwtConstants;
import com.project.bumawiki.global.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@ServiceWithTransactionalReadOnly
@RequiredArgsConstructor
public class UserService {

    private final JwtUtil jwtUtil;
    private final AuthIdRepository authIdRepository;

    public void checkIsLoginUser(String bearer){
        String authId = jwtUtil.getJwtBody(bearer).get(JwtConstants.AUTH_ID.message).toString();

        authIdRepository.findByAuthId(authId)
                .orElseThrow(() -> UserNotLoginException.EXCEPTION);
    }

}
