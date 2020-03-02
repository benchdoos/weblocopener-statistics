package com.github.benchdoos.weblocopenerstatistics.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "application_logins")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ApplicationLogin {
    @Id
    private UUID id;

    private Long loginCounts;

    @LastModifiedDate
    private Date firstTimeSeen;

    @CreatedDate
    private Date lastTimeSeen;

    private String countryCode;

    private String selectedLanguage;

    private String applicationVersion;
}
