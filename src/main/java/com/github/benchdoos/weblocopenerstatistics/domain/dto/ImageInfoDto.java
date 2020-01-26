package com.github.benchdoos.weblocopenerstatistics.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ImageInfoDto {
    private String imageUrl;
    private String deleteImageUrl;
}
