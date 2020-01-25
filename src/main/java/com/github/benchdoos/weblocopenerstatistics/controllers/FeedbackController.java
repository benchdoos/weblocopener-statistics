package com.github.benchdoos.weblocopenerstatistics.controllers;

import com.github.benchdoos.weblocopenerstatistics.domain.Feedback;
import com.github.benchdoos.weblocopenerstatistics.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    private final FeedbackRepository feedbackRepository;

    @PostMapping
    public void save(@RequestBody Feedback feedback) {
        feedbackRepository.save(feedback);
    }

}

