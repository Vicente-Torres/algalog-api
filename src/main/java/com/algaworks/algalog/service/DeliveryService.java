package com.algaworks.algalog.service;

import com.algaworks.algalog.model.entity.Delivery;
import com.algaworks.algalog.model.eum.DeliveryStatus;
import com.algaworks.algalog.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class DeliveryService {

    private ClientService clientService;
    private DeliveryRepository repository;

    @Transactional
    public Delivery request(Delivery delivery) {
        var client = clientService.findById(delivery.getClient().getId(), HttpStatus.BAD_REQUEST);

        delivery.setClient(client);
        delivery.setStatus(DeliveryStatus.PENDING);
        delivery.setRequestDate(LocalDateTime.now());

        return repository.save(delivery);
    }

}
