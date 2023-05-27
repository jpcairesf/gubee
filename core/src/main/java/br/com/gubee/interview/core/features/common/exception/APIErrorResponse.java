package br.com.gubee.interview.core.features.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class APIErrorResponse {

    private String message;
    private int code;
    private String status;
    private LocalDateTime timestamp;
    private String description;

}
