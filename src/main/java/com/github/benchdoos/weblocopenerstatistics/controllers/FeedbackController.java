package com.github.benchdoos.weblocopenerstatistics.controllers;

import com.github.benchdoos.weblocopenerstatistics.config.AuthoritiesConstants;
import com.github.benchdoos.weblocopenerstatistics.domain.Feedback;
import com.github.benchdoos.weblocopenerstatistics.domain.dto.FeedbackDto;
import com.github.benchdoos.weblocopenerstatistics.services.FeedbackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    @Secured(AuthoritiesConstants.READ_STATISTICS)
    @PostMapping
    public UUID createFeedback(@Valid @RequestBody FeedbackDto feedbackDto) {
        return feedbackService.createFeedback(feedbackDto);
    }

    @Secured(AuthoritiesConstants.READ_STATISTICS)
    @GetMapping("/list")
    public Page<Feedback> getFeedbacks(@PageableDefault Pageable page) {
        return feedbackService.getFeedbacks(page);
    }

    @Secured(AuthoritiesConstants.READ_STATISTICS)
    @GetMapping("/{uuid}")
    public Feedback getFeedback(@PathVariable @NotNull UUID uuid) {
        return feedbackService.getFeedbackById(uuid);
    }

    @Secured({AuthoritiesConstants.UPDATE_STATISTICS})
    @PostMapping("/{uuid}/seen")
    public Feedback setSeen(@PathVariable UUID uuid) {
        return feedbackService.updateSeen(uuid);
    }

    @Secured(AuthoritiesConstants.UPDATE_STATISTICS)
    @PostMapping("/all/seen")
    public Page<Feedback> setSeenAll(@PageableDefault Pageable page) {
        return feedbackService.updateSeenAll(page);
    }
}

