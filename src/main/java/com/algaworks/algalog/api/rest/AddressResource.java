package com.algaworks.algalog.api.rest;

import com.algaworks.algalog.model.Address;
import com.algaworks.algalog.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/enderecos")
public class AddressResource {

    private AddressService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Address save(@Valid @RequestBody Address address) {
        return service.save(address);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> update(@PathVariable Long id, @Valid @RequestBody Address address) {
        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        address.setId(id);
        return ResponseEntity.ok(service.save(address));
    }

}
