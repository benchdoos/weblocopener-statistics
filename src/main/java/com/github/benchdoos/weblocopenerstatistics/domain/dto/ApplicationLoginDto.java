package com.github.benchdoos.weblocopenerstatistics.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApplicationLoginDto {

    private String countryName;

    private String selectedLanguage;

    private String applicationVersion;
}
