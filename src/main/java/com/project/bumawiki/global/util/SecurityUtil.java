package com.project.bumawiki.global.util;


import com.project.bumawiki.domain.user.exception.UserNotLoginException;
import com.project.bumawiki.global.security.auth.AuthDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static AuthDetails getCurrentUser() {
        try {
            return (AuthDetails) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();
        }catch(ClassCastException e){
            throw UserNotLoginException.EXCEPTION;
        }
    }
}
