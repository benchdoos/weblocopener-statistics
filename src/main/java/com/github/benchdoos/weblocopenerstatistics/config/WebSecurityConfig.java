package com.github.benchdoos.weblocopenerstatistics.config;

import com.github.benchdoos.weblocopenerstatistics.config.properties.ClientProperties;
import com.github.benchdoos.weblocopenerstatistics.services.UserService;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final Environment environment;

    private final UserService userService;

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
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .oauth2Client();
    }


    @Bean
    public OAuth2AuthorizedClientService authorizedClientService() {

        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository());
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        final List<ClientRegistration> registrations = clientProperties.getClients().stream()
                .map(this::getRegistration)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return new InMemoryClientRegistrationRepository(registrations);
    }

    private void configureForSwagger(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(
                        "/v2/api-docs",
                        "/swagger-resources/**",
                        "/swagger-ui.html",
                        "/webjars/**", "/csrf").permitAll();
    }

    private ClientRegistration getRegistration(ClientProperties.ClientInfo client) {

        if (client == null) {
            return null;
        }

        final ClientRegistration.Builder builder = ClientRegistration.withRegistrationId(client.getClientId());
        builder
                .clientId(client.getClientId())
                .clientSecret(client.getClientSecret())
                .scope(client.getScopes())
                .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
                .redirectUriTemplate(client.getRedirectUriTemplate())
                .authorizationUri(client.getAuthorizationUri())
                .tokenUri(client.getTokenUri());

        if (client.getAuthorizationGrantType() != null) {
            log.info("AuthorizationGrantType for client {} is: {}", client.getClientName(), client.getAuthorizationGrantType().getValue());
            builder.authorizationGrantType(client.getAuthorizationGrantType());
        } else {
            builder.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE);
        }

        return builder.build();
    }

    @Bean
    public UserDetailsService users() {
        return userService;
    }
}
