package com.github.benchdoos.weblocopenerstatistics.repository;

import com.github.benchdoos.weblocopenerstatistics.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
