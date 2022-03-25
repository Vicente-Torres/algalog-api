package com.algaworks.algalog.api.rest;

import com.algaworks.algalog.model.Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClientController {

    @GetMapping
    public List<Client> list() {
        var client = new Client(1L, "88888", "teste@tes.com", "Teste");
        return List.of(client);
    }

}
