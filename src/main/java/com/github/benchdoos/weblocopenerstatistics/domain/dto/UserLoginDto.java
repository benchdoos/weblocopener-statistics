package com.github.benchdoos.weblocopenerstatistics.domain.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLoginDto {

    @NotNull
    private String countryName;

    @NotNull
    private String selectedLanguage;

    @NotNull
    private String applicationVersion;
}
