package com.algaworks.algalog.service;

import com.algaworks.algalog.exceptionhandler.BusinessException;
import com.algaworks.algalog.model.Recipient;
import com.algaworks.algalog.repository.RecipientRepository;
import com.algaworks.algalog.util.MessageHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class RecipientService {

    private MessageHandler messageHandler;
    private RecipientRepository repository;
    private AddressService addressService;

    public List<Recipient> findAll() {
        return repository.findAll();
    }

    public Recipient findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new BusinessException(messageHandler.getMessage("recipient.not.found"), HttpStatus.NOT_FOUND));
    }

    @Transactional
    public Recipient save(Recipient recipient) {
        var address = addressService.findById(recipient.getAddress().getId());
        var recipientSaved = repository.save(recipient);
        recipientSaved.setAddress(address);
        return recipientSaved;
    }

    @Transactional
    public Recipient update(Long id, Recipient recipient) {
        if (isNull(recipient.getId()) || !recipient.getId().equals(id)) {
            throw new BusinessException(messageHandler.getMessage("recipient.update.ids.conflict"));
        }

        if (!existsById(id)) {
            throw new BusinessException(messageHandler.getMessage("recipient.not.found"));
        }
        return save(recipient);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!existsById(id)) {
            throw new BusinessException(messageHandler.getMessage("recipient.not.found"), HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
    }

    private Boolean existsById(Long id) {
        return repository.existsById(id);
    }

}