package com.github.benchdoos.weblocopenerstatistics.repository;

import com.github.benchdoos.weblocopenerstatistics.domain.ApplicationLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsRepository extends JpaRepository<ApplicationLogin, Long> {
}
