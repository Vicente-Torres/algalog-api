package com.algaworks.algalog.exceptionhandler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

    @Getter
    private HttpStatus statusError;

    public BusinessException(String message) {
        super(message);
        this.statusError = HttpStatus.BAD_REQUEST;
    }

    public BusinessException(String message, HttpStatus statusError) {
        super(message);
        this.statusError = statusError;
    }

}
