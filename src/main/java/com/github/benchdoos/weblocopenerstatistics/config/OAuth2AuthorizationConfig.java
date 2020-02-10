package com.github.benchdoos.weblocopenerstatistics.config;

import com.github.benchdoos.weblocopenerstatistics.domain.enumirations.AuthorizedGrantTypes;
import com.github.benchdoos.weblocopenerstatistics.domain.enumirations.Clients;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@EnableWebSecurity
@Component
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    public static final int WEEK_AS_SECONDS = 604800;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TokenStore tokenStore;
    /**
     * В конфиге должны быть прописаны уже кодированные
     * {@link AuthorizationServerSecurityConfigurer#passwordEncoder(PasswordEncoder)} client-secrets.
     * Данные дефолты предоставлены для понимания, какой секрет нужно прописывать в клиентских приложениях.
     */
    @Value("${secret.web-app:994331a6-7062-4dbc-9ad8-780cc89dd1b4")
    private String weblocopenerApplicationClient;
    @Value("${token.access.validitySeconds:7200}")
    private int accessTokenValiditySeconds;
    @Value("${token.refresh.validitySeconds:86400}")
    private int refreshTokenValiditySeconds;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(Clients.WEBLOCOPENER_APPLICATION.toString())
                .secret(weblocopenerApplicationClient)
                .authorizedGrantTypes(AuthorizedGrantTypes.PASSWORD.getValue(), AuthorizedGrantTypes.REFRESH_TOKEN.getValue())
                .accessTokenValiditySeconds(accessTokenValiditySeconds)
                .refreshTokenValiditySeconds(refreshTokenValiditySeconds);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .tokenStore(tokenStore)
                .userDetailsService(userDetailsService)
                .authenticationManager(authenticationManager);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                .passwordEncoder(passwordEncoder)
                .allowFormAuthenticationForClients();
    }

}
