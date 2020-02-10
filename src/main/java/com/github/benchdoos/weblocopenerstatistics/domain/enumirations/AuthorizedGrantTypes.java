package com.github.benchdoos.weblocopenerstatistics.domain.enumirations;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AuthorizedGrantTypes {
    PASSWORD("password"),
    REFRESH_TOKEN("refresh_token");

    private final String value;

}
