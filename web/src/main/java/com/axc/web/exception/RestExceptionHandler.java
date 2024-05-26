package com.axc.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestErrorInfo> handleInternalServerError(Exception e) {
        logException(e);
        return createResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    private void logException(Exception e) {
        log.error(ExceptionUtils.getStackTrace(e));
        log.error(createLogInfo(e.getClass().toString(), e.getLocalizedMessage(), e.getMessage()));
    }

    private String createLogInfo(String exceptionName, String exceptionMessage, String exceptionReason) {
        return exceptionName + " handler: [" + exceptionMessage + "] reason: [" + exceptionReason + "]";
    }

    private ResponseEntity<RestErrorInfo> createResponseEntity(HttpStatus status, String message) {
        return new ResponseEntity<>(new RestErrorInfo(message), status);
    }
}
