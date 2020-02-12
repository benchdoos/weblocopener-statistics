package com.github.benchdoos.weblocopenerstatistics.controllers;

import com.github.benchdoos.weblocopenerstatistics.config.AuthoritiesConstants;
import com.github.benchdoos.weblocopenerstatistics.domain.dto.StatisticsReportDto;
import com.github.benchdoos.weblocopenerstatistics.services.StatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.benchdoos.weblocopenerstatistics.config.AuthoritiesConstants.*;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;

    @Secured({ROLE_ADMIN, ROLE_STATISTICS_VIEWER})
    @GetMapping("/report")
    public StatisticsReportDto getStatisticsReport() {
        return statisticsService.getFullReport();
    }
}
