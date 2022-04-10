package com.algaworks.algalog.api.rest;

import com.algaworks.algalog.model.Recipient;
import com.algaworks.algalog.service.RecipientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/destinatarios")
public class RecipientResource {

    private RecipientService service;

    @GetMapping
    public List<Recipient> findAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Recipient save(@RequestBody Recipient recipient) {
        return service.save(recipient);
    }

    @GetMapping("/{id}")
    public Recipient findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipient> update(@PathVariable Long id, @Valid @RequestBody Recipient recipient) {
        return ResponseEntity.ok(service.update(id, recipient));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

}