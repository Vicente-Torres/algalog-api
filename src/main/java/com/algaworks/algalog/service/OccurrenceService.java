package com.algaworks.algalog.service;

import com.algaworks.algalog.model.entity.Occurrence;
import com.algaworks.algalog.repository.OccurrenceRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class OccurrenceService {

    private DeliveryService deliveryService;

    private OccurrenceRepository repository;

    @Transactional
    public Occurrence registerOccurrence(Long deliveryId, String description) {
        var delivery = deliveryService.findById(deliveryId, HttpStatus.NOT_FOUND);
        var occurrence = new Occurrence(description, delivery, Instant.now());
        delivery.getOccurrences().add(occurrence);
        return occurrence;
    }

    public List<Occurrence> findByDeliveryId(Long deliveryId) {
        return repository.findByDeliveryId(deliveryId);
    }

}
