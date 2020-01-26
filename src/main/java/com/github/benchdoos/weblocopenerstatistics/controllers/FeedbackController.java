package com.github.benchdoos.weblocopenerstatistics.controllers;

import com.github.benchdoos.weblocopenerstatistics.domain.dto.FeedbackDto;
import com.github.benchdoos.weblocopenerstatistics.repository.FeedbackRepository;
import com.github.benchdoos.weblocopenerstatistics.services.FeedbackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    @PostMapping
    public UUID createFeedback(@Valid @RequestBody FeedbackDto feedbackDto) {
         return feedbackService.createFeedback(feedbackDto);
    }

}

