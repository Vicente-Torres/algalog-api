package com.algaworks.algalog.api.rest;

import com.algaworks.algalog.model.entity.Delivery;
import com.algaworks.algalog.service.DeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/entregas")
public class DeliveryResource {

    private DeliveryService service;

    @GetMapping
    public List<Delivery> listAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Delivery request(@Valid @RequestBody Delivery delivery) {
        return service.request(delivery);
    }

    @GetMapping("/{id}")
    public Delivery findById(@PathVariable Long id) {
        return service.findById(id, HttpStatus.NOT_FOUND);

    }

}
