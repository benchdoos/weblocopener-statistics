package com.github.benchdoos.weblocopenerstatistics.config.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Validated
@Component
@ConfigurationProperties("security")
public class ClientProperties {

    @NotEmpty
    private final List<@NotNull ClientInfo> trustedClients;

    @Setter
    @Getter
    public static class ClientInfo {

        @NotBlank
        private String clientName;

        @NotBlank
        private String clientSecret;
    }
}
