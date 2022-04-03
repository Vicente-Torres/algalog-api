package com.algaworks.algalog.service;

import com.algaworks.algalog.exceptionhandler.BusinessException;
import com.algaworks.algalog.model.Address;
import com.algaworks.algalog.repository.AddressRepository;
import com.algaworks.algalog.util.MessageHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class AddressService {

    private AddressRepository repository;
    private MessageHandler messageHandler;

    public List<Address> findAll() {
        return repository.findAll();
    }

    public Address findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new BusinessException(messageHandler.getMessage("address.not.found"), HttpStatus.NOT_FOUND));
    }

    @Transactional
    public Address save(Address address) {
        if (addressAlreadyUsed(address)) {
            throw new BusinessException(messageHandler.getMessage("address.already.registered"));
        }

        return repository.save(address);
    }

    @Transactional
    public Address update(Long id, Address address) { //TODO Extrair validações de atualização de objeto para um Validator generico.
        if (isNull(address.getId()) || !address.getId().equals(id)) {
            throw new BusinessException(messageHandler.getMessage("address.update.ids.conflict"));
        }

        if (!existsById(id)) {
            throw new BusinessException("address.not.found");
        }
        return save(address);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!existsById(id)) {
            throw new BusinessException(messageHandler.getMessage("address.not.found"), HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
    }

    private Boolean existsById(Long id) {
        return repository.existsById(id);
    }

    private boolean addressAlreadyUsed(Address address) {
        return repository.findByDistrictAndStreetAndNumberAndComplement(address.getDistrict(), address.getStreet(), address.getNumber(), address.getComplement())
                .stream()
                .anyMatch(addressRegistered -> !addressRegistered.equals(address));
    }
}
