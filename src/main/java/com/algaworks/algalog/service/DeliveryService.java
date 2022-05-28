package com.algaworks.algalog.service;

import com.algaworks.algalog.exceptionhandler.BusinessException;
import com.algaworks.algalog.model.entity.Delivery;
import com.algaworks.algalog.model.eum.DeliveryStatus;
import com.algaworks.algalog.repository.DeliveryRepository;
import com.algaworks.algalog.util.MessageHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

import static com.algaworks.algalog.model.eum.DeliveryStatus.FINISHED;
import static com.algaworks.algalog.model.eum.DeliveryStatus.PENDING;

@Service
@AllArgsConstructor
public class DeliveryService {

    private ClientService clientService;
    private DeliveryRepository repository;
    private MessageHandler messageHandler;

    @Transactional
    public Delivery request(Delivery delivery) {
        var client = clientService.findById(delivery.getClient().getId(), HttpStatus.BAD_REQUEST);

        delivery.setClient(client);
        delivery.setStatus(DeliveryStatus.PENDING);
        delivery.setRequestDate(Instant.now());

        return repository.save(delivery);
    }

    public List<Delivery> findAll(){
        return repository.findAll();
    }

    public Delivery findById(Long id, HttpStatus statusIfDeliveryNotExist) {
        return repository.findById(id).orElseThrow(() -> new BusinessException(messageHandler.getMessage("delivery.not.found"), statusIfDeliveryNotExist));
    }

    @Transactional
    public void finishingDelivery(Long deliveryId) {
        var delivery = findById(deliveryId, HttpStatus.NOT_FOUND);

        if(!delivery.getStatus().equals(PENDING)){
            throw new BusinessException("A entrega não pode ser finalizada", HttpStatus.BAD_REQUEST);
        }
        delivery.setStatus(FINISHED);
        delivery.setFinishedDate(Instant.now());
        repository.save(delivery);
    }

}
