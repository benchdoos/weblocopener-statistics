package com.github.benchdoos.weblocopenerstatistics.domain.enumirations;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Client {

    WEBLOCOPENER_APPLICATION("Application instance"),
    REST_CLIENT("Any user, admin or not");

    private final String description;
}
