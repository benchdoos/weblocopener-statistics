package com.github.benchdoos.weblocopenerstatistics.services;

import com.github.benchdoos.weblocopenerstatistics.domain.Feedback;
import com.github.benchdoos.weblocopenerstatistics.domain.dto.FeedbackDto;
import com.github.benchdoos.weblocopenerstatistics.mappers.FeedbackDtoToFeedbackMapper;
import com.github.benchdoos.weblocopenerstatistics.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final FeedbackDtoToFeedbackMapper feedbackMapper = Mappers.getMapper(FeedbackDtoToFeedbackMapper.class);

    public UUID createFeedback(FeedbackDto feedbackDto) {
        final Feedback feedback = feedbackMapper.convert(feedbackDto);
        feedback.setDate(new Date());

        final Feedback save = feedbackRepository.save(feedback);
        return save.getId();
    }
}
