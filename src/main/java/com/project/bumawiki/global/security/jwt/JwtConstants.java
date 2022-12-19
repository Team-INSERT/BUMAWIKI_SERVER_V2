package com.project.bumawiki.global.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum JwtConstants {

    AUTH_ID("auth_id"),
    TYPE("type"),
    EMPTY(" "),
    PREFIX("prefix"),
    ROLE("role"),
    ACCESS_KEY("access_token"),
    REFRESH_KEY("refresh_token");


    public String message;


}
