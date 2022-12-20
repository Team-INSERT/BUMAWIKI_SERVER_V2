package com.project.bumawiki.domain.auth.domain;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Id;

@RedisHash
public class AuthId {

    @Id
    String id;

    @Indexed
    String authId;


    public AuthId update(String authId) {
        this.authId = authId;
        return this;
    }
}
