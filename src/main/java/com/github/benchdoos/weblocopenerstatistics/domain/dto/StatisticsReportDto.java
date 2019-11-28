package com.github.benchdoos.weblocopenerstatistics.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StatisticsReportDto {
    private Map<String, Object> statistics;
    private Date generationDate;
}
