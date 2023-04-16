package com.project.bumawiki.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    //Image
    NO_IMAGE(400,"IMG-400-1","NO Image"),

    //Like
    ALREADY_THUMBS_UP(400,"THUMBSUP-400-1","Already Thumbs Up This Docs"),
    YOU_DONT_THUMBS_UP_THIS_DOCS(400,"THUMBSUP-400-2","You Dont Thumbs Up This Docs"),
    NO_DOCS_YOU_THUMBS_UP(404,"THUMBSUP-404-1","No Docs You Thumbs Up"),

    //Docs
    DOCS_NOT_FOUND(404, "DOCS-404-2", "Docs Not Found"),
    DOCS_TYPE_NOT_FOUND(404, "DOCS-404-3", "Docs Type Not Found"),
    NO_UPDATABLE_DOCS(403, "DOCS-403-1", "No Docs You Want To Update"),
    POST_TITLE_ALREADY_EXIST(403, "DOCS-403-1", "Post_Already_Exist"),
    CANNOT_CHANGE_YOUR_DOCS(403, "DOCS-403-2", "Cannot Change Your Docs"),

    FORBIDDEN(403, "COMMON-403-1", "Forbidden"),

    //User
    USER_NOT_LOGIN(403,"USER-403-1", "User Not Login"),
    USER_NOT_FOUND(404, "USER-404-1", "User Not Found"),

    //JWT
    INVALID_TOKEN(403, "TOKEN-403-1", "Access with Invalid Token"),
    EXPIRED_JWT(403, "TOKEN-403-2", "Access Token Expired"),
    REFRESH_TOKEN_EXPIRED(403, "TOKEN-403-3", "Refresh Token Expired"),

    //ServerError
    INVALID_ARGUMENT(400, "ARG-400-1", "Arg Is Not Valid"),
    BSM_AUTH_INVALID_CLIENT(500, "BSM-500-1", "Bsm Client Is Invalid"),
    INTERNAL_SERVER_ERROR(500, "SERVER-500-1", "Internal Server Error");

    private final int status;
    private final String code;
    private final String message;
}
