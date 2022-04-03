package com.algaworks.algalog.api.rest;

import com.algaworks.algalog.model.Address;
import com.algaworks.algalog.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/enderecos")
public class AddressResource {

    private AddressService service;


    @GetMapping
    public List<Address> findAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Address save(@Valid @RequestBody Address address) {
        return service.save(address);
    }

    @GetMapping("/{id}")
    public Address findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public Address update(@PathVariable Long id, @Valid @RequestBody Address address) {
        return service.update(id, address);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

}
