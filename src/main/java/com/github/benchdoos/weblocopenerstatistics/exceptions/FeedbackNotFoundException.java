package com.github.benchdoos.weblocopenerstatistics.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Feedback with given credentials not found")
public class FeedbackNotFoundException extends RuntimeException{
}
