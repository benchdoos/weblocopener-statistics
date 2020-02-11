package com.github.benchdoos.weblocopenerstatistics.config;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@EnableResourceServer //todo change annotation
@RequiredArgsConstructor
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private final Environment environment;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //checking if swagger profile is active
        final boolean isSwaggerActive = Arrays.stream(environment.getActiveProfiles())
                .anyMatch("swagger"::equalsIgnoreCase);
        if (isSwaggerActive) {
            configureForSwagger(http);

        }
    }

    private void configureForSwagger(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(
                        "/v2/api-docs",
                        "/swagger-resources/**",
                        "/swagger-ui.html",
                        "/webjars/**", "/csrf").permitAll();
    }

}
