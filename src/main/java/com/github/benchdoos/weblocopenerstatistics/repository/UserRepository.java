package com.github.benchdoos.weblocopenerstatistics.repository;

import com.github.benchdoos.weblocopenerstatistics.domain.User;
import com.github.benchdoos.weblocopenerstatistics.domain.projections.CountryLoginsView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findFirstByOrderByLoginCountsAsc();

    User findFirstByOrderByLoginCountsDesc();

    List<User> findAllByLastTimeSeenBetween(Date from, Date to);

    @Query("select distinct u.countryCode from User u")
    List<String> findUniqueCountyCodes();

    @Query("select " +
            "new com.github.benchdoos.weblocopenerstatistics.domain.projections.CountryLoginsView(countryCode, count(loginCounts)) " +
            "from User " +
            "group by countryCode")
    List<CountryLoginsView> countUsersByCountryCodes(List<String> countryCodes);

    Long countByCountryCodeIn(List<String> codes);
}
