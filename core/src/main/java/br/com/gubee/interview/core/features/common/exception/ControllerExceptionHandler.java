package br.com.gubee.interview.core.features.common.exception;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@CommonsLog
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<APIErrorResponse> handleOptionalNotFoundException(Exception ex, WebRequest request) {
        return this.handleError(HttpStatus.NOT_FOUND, ex, request);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<APIErrorResponse> handleRuntimeException(RuntimeException ex, WebRequest request) {
        return this.handleError(HttpStatus.INTERNAL_SERVER_ERROR, ex, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<APIErrorResponse> handleBusinessException(BusinessException ex, WebRequest request) {
        return this.handleError(HttpStatus.BAD_REQUEST, ex, request);
    }

    private ResponseEntity<APIErrorResponse> handleError(HttpStatus status, Exception ex, WebRequest request) {
        log.error(ex);
        return this.getResponse(status, ex, request);
    }

    private ResponseEntity<APIErrorResponse> getResponse(HttpStatus status, Exception ex, WebRequest request) {
        APIErrorResponse message = new APIErrorResponse(
                ex.getMessage(),
                status.value(),
                status.getReasonPhrase(),
                LocalDateTime.now(),
                request.getDescription(false));
        return ResponseEntity.status(status).body(message);
    }

}
