package com.algaworks.algalog.service;

import com.algaworks.algalog.exceptionhandler.BusinessException;
import com.algaworks.algalog.model.Client;
import com.algaworks.algalog.repository.ClientRepository;
import com.algaworks.algalog.util.MessageHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class ClientService {

    private ClientRepository repository;
    private MessageHandler messageHandler;

    public List<Client> findAll() {
        return repository.findAll();
    }

    public Client findById(Long id, HttpStatus statusIfClientNotExist) {
        return repository.findById(id).orElseThrow(() -> new BusinessException(messageHandler.getMessage("client.not.found"), statusIfClientNotExist));
    }

    @Transactional
    public Client save(Client client) {
        if (emailAlreadyUsed(client)) {
            throw new BusinessException(messageHandler.getMessage("client.email.already.registered"));
        }

        if (phoneAlreadyUsed(client)) {
            throw new BusinessException(messageHandler.getMessage("client.phone.already.registered"));
        }

        return repository.save(client);
    }

    @Transactional
    public Client update(Long id, Client client) {
        if (isNull(client.getId()) || !client.getId().equals(id)) {
            throw new BusinessException(messageHandler.getMessage("client.update.ids.conflict"));
        }

        if (!existsById(id)) {
            throw new BusinessException("client.not.found");
        }
        return save(client);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!existsById(id)) {
            throw new BusinessException(messageHandler.getMessage("client.not.found"), HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
    }

    private Boolean existsById(Long id) {
        return repository.existsById(id);
    }

    private boolean emailAlreadyUsed(Client client) {
        return repository.findByEmail(client.getEmail()).stream()
                .anyMatch(clientRegistered -> !clientRegistered.equals(client) && !clientRegistered.getId().equals(client.getId()));
    }

    private boolean phoneAlreadyUsed(Client client) {
        return repository.findByPhone(client.getPhone()).stream()
                .anyMatch(clientRegistered -> !clientRegistered.equals(client) && !clientRegistered.getId().equals(client.getId()));
    }

}
