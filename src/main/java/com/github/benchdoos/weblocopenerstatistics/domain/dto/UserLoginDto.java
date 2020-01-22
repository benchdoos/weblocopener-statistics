package com.github.benchdoos.weblocopenerstatistics.domain.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLoginDto {

    private String countryName;

    private String selectedLanguage;

    private String applicationVersion;
}
