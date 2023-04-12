package com.project.bumawiki.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    VERSION_NOT_EXIST(400,"DOCS-400-1","Version Not Exist"),
    NO_IMAGE(400,"IMG-400-1","NO Image"),
    DOCS_NOT_FOUND(404, "DOCS-404-2", "Docs Not Found"),
    DOCS_TYPE_NOT_FOUND(404, "DOCS-404-3", "Docs Type Not Found"),
    NO_UPDATABLE_POST(403, "DOCS-403-1", "No Post You Want To Update"),
    POST_TITLE_ALREADY_EXIST(403, "DOCS-403-1", "Post_Already_Exist"),
    FORBIDDEN(403, "COMMON-403-1", "Forbidden"),
    USER_NOT_LOGIN(403,"USER-403-1", "User Not Login"),
    CANNOT_CHANGE_YOUR_DOCS(403, "DOCS-403-2", "Cannot Change Your Docs"),
    INVALID_TOKEN(403, "TOKEN-403-1", "Access with Invalid Token"),
    EXPIRED_JWT(403, "TOKEN-403-2", "Access Token Expired"),
    REFRESH_TOKEN_EXPIRED(403, "TOKEN-403-3", "Refresh Token Expired"),
    USER_NOT_FOUND(404, "USER-404-1", "User Not Found"),
    BSM_AUTH_INVALID_CLIENT(500, "BSM-500-1", "Bsm Client Is Invalid"),
    INTERNAL_SERVER_ERROR(500, "SERVER-500-1", "Internal Server Error");

    private final int status;
    private final String code;
    private final String message;
}
