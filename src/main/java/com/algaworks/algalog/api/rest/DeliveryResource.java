package com.algaworks.algalog.api.rest;

import com.algaworks.algalog.model.dto.input.DeliveryInput;
import com.algaworks.algalog.model.dto.response.DeliveryResponse;
import com.algaworks.algalog.service.DeliveryService;
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

}
