package com.project.bumawiki.domain.auth.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
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
