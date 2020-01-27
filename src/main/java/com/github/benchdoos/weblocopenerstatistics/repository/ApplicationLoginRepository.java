package com.github.benchdoos.weblocopenerstatistics.repository;

import com.github.benchdoos.weblocopenerstatistics.domain.ApplicationLogin;
import com.github.benchdoos.weblocopenerstatistics.domain.projections.CountryLoginsView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface ApplicationLoginRepository extends JpaRepository<ApplicationLogin, UUID> {

    ApplicationLogin findFirstByOrderByLoginCountsAsc();

    ApplicationLogin findFirstByOrderByLoginCountsDesc();

    List<ApplicationLogin> findAllByLastTimeSeenBetween(Date from, Date to);

    @Query("select distinct u.countryCode from ApplicationLogin u")
    List<String> findUniqueCountyCodes();

    @Query("select " +
            "new com.github.benchdoos.weblocopenerstatistics.domain.projections.CountryLoginsView(countryCode, count(loginCounts)) " +
            "from ApplicationLogin " +
            "group by countryCode")
    List<CountryLoginsView> countUsersByCountryCodes(List<String> countryCodes);

    Long countByCountryCodeIn(List<String> codes);
}
