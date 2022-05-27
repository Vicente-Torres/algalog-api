package com.algaworks.algalog.api.rest;

import com.algaworks.algalog.model.dto.input.DeliveryInput;
import com.algaworks.algalog.model.dto.input.OccurrenceInput;
import com.algaworks.algalog.model.dto.response.DeliveryResponse;
import com.algaworks.algalog.model.dto.response.OccurrenceResponse;
import com.algaworks.algalog.service.DeliveryService;
import com.algaworks.algalog.service.OccurrenceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/entregas")
public class DeliveryResource {

    private DeliveryService service;

    private OccurrenceService occurrenceService;

    @GetMapping
    public List<DeliveryResponse> listAll() {
        return DeliveryResponse.toDTOCollection(service.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeliveryResponse request(@Valid @RequestBody DeliveryInput deliveryInput) {
        return DeliveryResponse.toDTO(service.request(DeliveryInput.toEntity(deliveryInput)));
    }

    @GetMapping("/{id}")
    public DeliveryResponse findById(@PathVariable Long id) {
        return DeliveryResponse.toDTO(service.findById(id, HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/ocorrencias")
    public OccurrenceResponse registerOccurrence(@PathVariable("id") Long deliveryId,
                                                 @Valid @RequestBody OccurrenceInput occurrenceInput) {
        var occurrence = occurrenceService.registerOccurrence(deliveryId, occurrenceInput.getDescription());
        return OccurrenceResponse.toDTO(occurrence);
    }

    @GetMapping("/{id}/ocorrencias")
    public List<OccurrenceResponse> findOccurrencesFromDelivery(@PathVariable("id") Long deliveryId) {
        var occurrences = occurrenceService.findByDeliveryId(deliveryId);
        return OccurrenceResponse.toDTOCollection(occurrences);
    }

}
