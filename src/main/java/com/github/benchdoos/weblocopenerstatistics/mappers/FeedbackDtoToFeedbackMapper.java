package com.github.benchdoos.weblocopenerstatistics.mappers;

import com.github.benchdoos.weblocopenerstatistics.domain.Feedback;
import com.github.benchdoos.weblocopenerstatistics.domain.ImageInfo;
import com.github.benchdoos.weblocopenerstatistics.domain.dto.FeedbackDto;
import com.github.benchdoos.weblocopenerstatistics.domain.dto.ImageInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FeedbackDtoToFeedbackMapper {

    Feedback convert(FeedbackDto feedbackDto);

    ImageInfo convert(ImageInfoDto imageInfoDto);
}
