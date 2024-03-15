package com.project.bumawiki.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    //Like
    ALREADY_THUMBS_UP(400, "THUMBSUP-400-1", "Already Thumbs Up This Docs"),
    YOU_DONT_THUMBS_UP_THIS_DOCS(400, "THUMBSUP-400-2", "You Dont Thumbs Up This Docs"),
    NO_DOCS_YOU_THUMBS_UP(404, "THUMBSUP-404-1", "No Docs You Thumbs Up"),

    //Docs
    NO_UPDATABLE_DOCS(403, "DOCS-403-1", "No Docs You Want To Update"),
    POST_TITLE_ALREADY_EXIST(403, "DOCS-403-1", "Post_Already_Exist"),
    CANNOT_CHANGE_YOUR_DOCS(403, "DOCS-403-2", "Cannot Change Your Docs"),
    DOCS_NOT_FOUND(404, "DOCS-404-2", "Docs Not Found"),
    DOCS_TYPE_NOT_FOUND(404, "DOCS-404-3", "Docs Type Not Found"),
    VERSION_NOT_EXIST(404, "DOCS-404-4", "Version Not Exist"),

    FORBIDDEN(403, "COMMON-403-1", "Forbidden"),

    //User
    USER_NOT_LOGIN(403, "USER-403-1", "User Not Login"),
    USER_NOT_FOUND(404, "USER-404-1", "User Not Found"),

    //JWT
    INVALID_TOKEN(403, "TOKEN-403-1", "Access with Invalid Token"),
    EXPIRED_JWT(403, "TOKEN-403-2", "Access Token Expired"),
    REFRESH_TOKEN_EXPIRED(403, "TOKEN-403-3", "Refresh Token Expired"),

    //ServerError,
    INVALID_ARGUMENT(400, "ARG-400-1", "Arg Is Not Valid"),
    BSM_AUTH_INVALID_CLIENT(500, "BSM-500-1", "Bsm Client Is Invalid"),
    INTERNAL_SERVER_ERROR(500, "SERVER-500-1", "Internal Server Error"),

    //Image
    NO_IMAGE(400, "IMG-400-1", "NO Image"),
    IMAGE_NOT_FOUND_EXCEPTION(404, "IMG-404-1", "Image Not Found"),
    S3_SAVE_EXCEPTION(500,"IMG-500-1" , "S3 Save Exception"),

    //coin
    MONEY_NOT_ENOUGH(400, "COIN-400-1", "Money Is Not Enough"),
    COIN_NOT_ENOUGH(400,"COIN-400-2" , "Coin Is Not Enough"),
    TRADE_ALREADY_FINISHED(400, "COIN-400-3", "Trade Already Finished"),
    ALREADY_AWARDED(400, "COIN-400-4", "Already Awarded"),
    ALREADY_CREATED(401, "COIN-401-1", "Account Already Created"),
    CANCEL_OTHERS_TRADE(403,"COIN-403-1" , "Cancel Others Trade"),
    COIN_ACCOUNT_NOT_FOUND_EXCEPTION(404, "COIN-404-1", "Coin Account Not Found"),
    PRICE_NOT_FOUND(404, "COIN-404-2", "Price Not Found"),
    TRADE_NOT_FOUND(404, "COIN-404-3", "Trade Not Found"),
    RANDOM_INSTANCE(500, "COIN-500-1", "Random Instance Create Exception");

    private final int status;
    private final String code;
    private final String message;
}
