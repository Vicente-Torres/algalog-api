package com.algaworks.algalog.exceptionhandler;

import com.algaworks.algalog.model.exception.Problem;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

@ControllerAdvice
@AllArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var messages = new ArrayList<String>();

        ex.getBindingResult().getAllErrors().forEach(objectError -> {
            var message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
            messages.add(message);
        });

        var title = messageSource.getMessage("argument.not.valid", null, LocaleContextHolder.getLocale());
        var problem = new Problem(status.value(), LocalDateTime.now(), title, messages);

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
        var problem = new Problem(ex.getStatusError().value(), LocalDateTime.now(), ex.getMessage());

        return handleExceptionInternal(ex, problem, new HttpHeaders(), ex.getStatusError(), request);

    }

}
