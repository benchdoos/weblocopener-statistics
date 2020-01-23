package com.github.benchdoos.weblocopenerstatistics.domain.projections;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CountryLoginsView {
    String countryCode;
    Long count;
}
