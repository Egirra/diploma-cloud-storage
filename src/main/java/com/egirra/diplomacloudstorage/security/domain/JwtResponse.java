package com.egirra.diplomacloudstorage.security.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtResponse {

    @JsonProperty("access-token")
    private String accessToken;
}