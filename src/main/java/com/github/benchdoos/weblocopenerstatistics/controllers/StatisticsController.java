package com.github.benchdoos.weblocopenerstatistics.controllers;

import com.github.benchdoos.weblocopenerstatistics.domain.dto.StatisticsReportDto;
import com.github.benchdoos.weblocopenerstatistics.services.StatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/statistics")
@Slf4j
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping("/report")
    public StatisticsReportDto getStatisticsReport() {
        return statisticsService.getFullReport();
    }
}
