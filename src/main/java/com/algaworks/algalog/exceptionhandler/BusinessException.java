package com.algaworks.algalog.exceptionhandler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

    @Getter
    private HttpStatus statusError;

    private final HttpStatus defaultStatusErro = HttpStatus.BAD_REQUEST;

    public BusinessException(String message, HttpStatus... statusError) {
        super(message);
        if (statusError.length > 1) {
            throw new RuntimeException("É esperado apenas um status HTTP para construtor BusinessException");
        }
        this.statusError = (statusError.length == 1) ? statusError[0] : defaultStatusErro;
    }

}