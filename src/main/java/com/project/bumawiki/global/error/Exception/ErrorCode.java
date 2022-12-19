package com.project.bumawiki.global.error.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    USER_NOT_FOUND(404, "USER-404-1", "User Not Found");

    private final int status;
    private final String code;
    private final String message;
}
