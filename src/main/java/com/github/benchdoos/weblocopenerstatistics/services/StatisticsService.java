package com.github.benchdoos.weblocopenerstatistics.services;

import com.github.benchdoos.weblocopenerstatistics.domain.User;
import com.github.benchdoos.weblocopenerstatistics.domain.dto.StatisticsReportDto;
import com.github.benchdoos.weblocopenerstatistics.domain.projections.CountryLoginsView;
import com.github.benchdoos.weblocopenerstatistics.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticsService {
    private final UserRepository userRepository;

    public StatisticsReportDto getFullReport() {
        final Map<String, Object> statistics = new HashMap<>();

        final List<User> all = userRepository.findAll();

        statistics.put("TOTAL_REGISTERED", all.size());

        statistics.put("USERS_BY_COUNTRIES", getCountriesWithUsersCount());

//        statistics.put("USERS_AVERAGE_LOGIN_COUNT", userRepository.countAllByLoginCounts() / all.size());
        statistics.put("USERS_MINIMAL_LOGIN_COUNT", userRepository.findFirstByOrderByLoginCountsDesc().getLoginCounts());
        statistics.put("USERS_MAXIMAL_LOGIN_COUNT", userRepository.findFirstByOrderByLoginCountsAsc().getLoginCounts());

        //todo change this
        statistics.put("USERS_SEEN_WITHIN_A_MONTH", userRepository.findAllByLastTimeSeenBetween(new Date(), new Date()));

        return StatisticsReportDto.builder().statistics(statistics).generationDate(new Date()).build();
    }


    private List<CountryLoginsView> getCountriesWithUsersCount() {
        final List<String> uniqueCountyCodes = userRepository.findUniqueCountyCodes();
        final Long aLong = userRepository.countByCountryCodeIn(uniqueCountyCodes);
        return userRepository.countUsersByCountryCodes(uniqueCountyCodes);
    }
}
