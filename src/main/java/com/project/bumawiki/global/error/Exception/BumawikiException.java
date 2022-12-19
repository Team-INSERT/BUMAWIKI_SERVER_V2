package com.project.bumawiki.global.error.Exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BumawikiException extends RuntimeException{
    private final ErrorCode errorCode;
}
