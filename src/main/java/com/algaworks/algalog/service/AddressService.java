package com.algaworks.algalog.service;

import com.algaworks.algalog.exceptionhandler.BusinessException;
import com.algaworks.algalog.model.Address;
import com.algaworks.algalog.repository.AddressRepository;
import com.algaworks.algalog.util.MessageHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AddressService {

    private AddressRepository repository;
    private MessageHandler messageHandler;

    @Transactional
    public Address save(Address address) {
        if (addressAlreadyUsed(address)) {
            throw new BusinessException(messageHandler.getMessage("address.already.registered"));
        }

        return repository.save(address);
    }

    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }

    private boolean addressAlreadyUsed(Address address) {
        return repository.findByDistrictAndStreetAndNumber(address.getDistrict(), address.getStreet(), address.getNumber())
            .stream()
            .anyMatch(addressRegistered -> !addressRegistered.equals(address));
    }

}
