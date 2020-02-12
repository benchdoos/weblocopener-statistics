package com.github.benchdoos.weblocopenerstatistics.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

/**
 * User role
 */
@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Nullable
    @Column(name = "description")
    private String description;

    @Override
    public String getAuthority() {
        return this.name;
    }
}
