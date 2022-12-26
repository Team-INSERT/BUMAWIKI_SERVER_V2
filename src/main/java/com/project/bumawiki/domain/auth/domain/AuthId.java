package com.project.bumawiki.domain.auth.domain;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Id;

@RedisHash
public class AuthId {

    @Id
    String id;

    @Indexed
    String authId;

    @TimeToLive
    private long ttl;

    public AuthId update(String authId, long ttl) {
        this.authId = authId;
        this.ttl = ttl;
        return this;
    }
}
