package com.github.benchdoos.weblocopenerstatistics.config;

/**
 * All authorities
 */
public class AuthoritiesConstants {

    /**
     * Admin, can do everything
     */
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    /**
     * WeblocOpenerCore application, can record logins
     */
    public static final String ROLE_WEBLOCOPENER_APPLICATION = "ROLE_WEBLOCOPENER_APPLICATION";

    /**
     * Web application, can convert links
     */
    public static final String ROLE_WEB_CLIENT = "ROLE_WEB_CLIENT";

    /**
     * User that can read statistics only
     */
    public static final String ROLE_STATISTICS_VIEWER = "ROLE_STATISTICS_VIEWER";

}
