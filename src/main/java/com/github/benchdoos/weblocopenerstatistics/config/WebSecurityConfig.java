package com.github.benchdoos.weblocopenerstatistics.config;

import com.github.benchdoos.weblocopenerstatistics.config.properties.ClientProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final RequestMatcher PUBLIC_URLS = new OrRequestMatcher(
            new AntPathRequestMatcher("/public/**"),
            new AntPathRequestMatcher("/webjars/**")
    );

    private static final RequestMatcher SWAGGER_URLS = new AndRequestMatcher(
            new AntPathRequestMatcher("/v2/api-docs"),
            new AntPathRequestMatcher("/swagger-resources/**"),
            new AntPathRequestMatcher("/swagger-ui.html"),
            new AntPathRequestMatcher("/csrf")

    );

    private final Environment environment;

    private final ClientProperties clientProperties;

    @Value("${token.access.validitySeconds:7200}")
    private int accessTokenValiditySeconds;
    @Value("${token.refresh.validitySeconds:86400}")
    private int refreshTokenValiditySeconds;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/webjars/**");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        final boolean isSwaggerActive = Arrays.stream(environment.getActiveProfiles())
                .anyMatch("swagger"::equalsIgnoreCase);
        if (isSwaggerActive) {
            configureForSwagger(http);
        }

        http.authorizeRequests()
                .requestMatchers(PUBLIC_URLS).permitAll()
                .antMatchers("/oauth/token").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin();
    }

    private void configureForSwagger(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers(new NegatedRequestMatcher(SWAGGER_URLS))
                .permitAll();
    }
}
