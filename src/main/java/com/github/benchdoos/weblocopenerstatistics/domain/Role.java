package com.github.benchdoos.weblocopenerstatistics.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * User roles
 */
public enum Role implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_USER,
    ROLE_WEBLOCOPENER_APP;

    @Override
    public String getAuthority() {
        return this.toString();
    }
}
