package com.github.benchdoos.weblocopenerstatistics.services;

import com.github.benchdoos.weblocopenerstatistics.domain.ApplicationLogin;
import com.github.benchdoos.weblocopenerstatistics.domain.dto.StatisticsReportDto;
import com.github.benchdoos.weblocopenerstatistics.domain.projections.CountryLoginsView;
import com.github.benchdoos.weblocopenerstatistics.repository.ApplicationLoginRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticsService {
    private final ApplicationLoginRepository applicationLoginRepository;

    public StatisticsReportDto getFullReport() {
        final Map<String, Object> statistics = new HashMap<>();

        final List<ApplicationLogin> all = applicationLoginRepository.findAll();

        statistics.put("TOTAL_REGISTERED", all.size());

        statistics.put("USERS_BY_COUNTRIES", getCountriesWithUsersCount());

        statistics.put("USERS_MINIMAL_LOGIN_COUNT", applicationLoginRepository.findFirstByOrderByLoginCountsDesc().getLoginCounts());
        statistics.put("USERS_MAXIMAL_LOGIN_COUNT", applicationLoginRepository.findFirstByOrderByLoginCountsAsc().getLoginCounts());

        final Date from = getMonthAgoDate();
        statistics.put("USERS_SEEN_WITHIN_A_MONTH", applicationLoginRepository.findAllByLastTimeSeenBetween(from, new Date()));

        return StatisticsReportDto.builder().statistics(statistics).generationDate(new Date()).build();
    }

    private Date getMonthAgoDate() {
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,-1);
        return calendar.getTime();
    }

    private List<CountryLoginsView> getCountriesWithUsersCount() {
        final List<String> uniqueCountyCodes = applicationLoginRepository.findUniqueCountyCodes();
        final Long aLong = applicationLoginRepository.countByCountryCodeIn(uniqueCountyCodes);
        return applicationLoginRepository.countUsersByCountryCodes(uniqueCountyCodes);
    }
}
