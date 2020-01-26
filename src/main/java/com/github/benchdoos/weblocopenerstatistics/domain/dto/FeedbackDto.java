package com.github.benchdoos.weblocopenerstatistics.domain.dto;

import com.github.benchdoos.weblocopenerstatistics.domain.ImageInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FeedbackDto {

    @NotNull
    private UUID userUuid;

    @NotBlank
    private String base64FeedbackMessage;

    private String base64LogFile;

    private String email;

    private List<ImageInfoDto> images;
}
