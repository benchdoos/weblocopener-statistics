package com.github.benchdoos.weblocopenerstatistics.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "feedbacks")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Feedback {
    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    private UUID userUuid;

    @Column(columnDefinition = "TEXT", length = 30000)
    private String feedbackMessage;

    @Column(columnDefinition = "TEXT", length = 30000)
    private String base64LogFile;

    private String email;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ImageInfo> images;

    @NotNull
    private Date date;

    private Boolean seen;
}
