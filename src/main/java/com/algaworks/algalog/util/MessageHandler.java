package com.algaworks.algalog.util;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MessageHandler {

    private MessageSource messageSource;

    public String getMessage(String messageIdentify) {
        return messageSource.getMessage(messageIdentify, null, LocaleContextHolder.getLocale());
    }

}
