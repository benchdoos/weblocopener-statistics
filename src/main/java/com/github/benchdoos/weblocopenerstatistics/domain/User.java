package com.github.benchdoos.weblocopenerstatistics.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    @Id
    @GeneratedValue
    private UUID id;

    private Long loginCounts;

    private Date firstTimeSeen;

    private Date lastTimeSeen;

    private String countryCode;

    private String selectedLanguage;

    private String applicationVersion;
}
