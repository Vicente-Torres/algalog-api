package com.algaworks.algalog.api.rest;

import com.algaworks.algalog.model.entity.Client;
import com.algaworks.algalog.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/clientes")
public class ClientResource {

    private ClientService service;

    @GetMapping
    public List<Client> listAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client save(@Valid @RequestBody Client client) {
        return service.save(client);
    }

    @GetMapping("/{id}")
    public Client findById(@PathVariable Long id) {
        return service.findById(id, HttpStatus.NOT_FOUND);

    }

    @PutMapping("/{id}")
    public Client update(@PathVariable Long id, @Valid @RequestBody Client client) {
        return service.update(id, client);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }

}
