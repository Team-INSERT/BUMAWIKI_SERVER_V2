package com.project.bumawiki.global.error.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {


    POST_TITLE_ALREADY_EXIST(403, "POST-403-1", "Post_Already_Exist"),
    FORBIDDEN(403, "COMMON-403-1", "Forbidden"),
    USER_NOT_LOGIN(403,"USER-403-1", "User Not Login"),
    INVALID_TOKEN(403, "TOKEN-403-1", "Access with Invalid Token"),
    USER_NOT_FOUND(404, "USER-404-1", "User Not Found"),
    BSM_AUTH_INVALID_CLIENT(500, "BSM-500-1", "Bsm Client Is Invalid"),
    INTERNAL_SERVER_ERROR(500, "SERVER-500-1", "Internal Server Error");

    private final int status;
    private final String code;
    private final String message;
}
