package com.github.benchdoos.weblocopenerstatistics.repository;

import com.github.benchdoos.weblocopenerstatistics.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Long countAllByLoginCounts();

    Long findFirstByLoginCountsOrderByLoginCountsDesc();
    Long findFirstByLoginCountsOrderByLoginCountsAsc();

    List<User> findAllByLastTimeSeenBetween(Date from, Date to);
}
