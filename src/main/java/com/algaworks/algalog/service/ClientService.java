package com.algaworks.algalog.service;

import com.algaworks.algalog.exceptionhandler.BusinessException;
import com.algaworks.algalog.model.Client;
import com.algaworks.algalog.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService {

    private ClientRepository repository;
    private MessageSource messageSource;

    public List<Client> findAll() {
        return repository.findAll();
    }

    public Optional<Client> findById(Long id) {
        return repository.findById(id);
    }

    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Transactional
    public Client save(Client client) {
        if (emailAlreadyUsed(client)) {
            throw new BusinessException(getMessage("client.email.already.registered"));
        }

        if (phoneAlreadyUsed(client)) {
            throw new BusinessException(getMessage("client.phone.already.registered"));
        }

        return repository.save(client);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private boolean emailAlreadyUsed(Client client) {
        return repository.findByEmail(client.getEmail()).stream()
            .anyMatch(clientRegistered -> !clientRegistered.equals(client));
    }

    private boolean phoneAlreadyUsed(Client client) {
        return repository.findByPhone(client.getPhone()).stream()
            .anyMatch(clientRegistered -> !clientRegistered.equals(client));
    }

    private String getMessage(String messageIdentify) {
        return messageSource.getMessage(messageIdentify, null, LocaleContextHolder.getLocale());
    }

}
