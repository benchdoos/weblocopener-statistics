package com.github.benchdoos.weblocopenerstatistics.services;

import com.github.benchdoos.weblocopenerstatistics.domain.Feedback;
import com.github.benchdoos.weblocopenerstatistics.domain.dto.FeedbackDto;
import com.github.benchdoos.weblocopenerstatistics.mappers.FeedbackDtoToFeedbackMapper;
import com.github.benchdoos.weblocopenerstatistics.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
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
        feedback.setFeedbackMessage(new String(Base64.getDecoder().decode(feedbackDto.getBase64FeedbackMessage()), StandardCharsets.UTF_8));

        final Feedback save = feedbackRepository.save(feedback);
        return save.getId();
    }

    public Page<Feedback> getFeedbacks(Pageable page) {
        return feedbackRepository.findAll(page);
    }

    public Feedback getFeedbackById(UUID uuid) {
        return feedbackRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
    }
}
