package com.algaworks.algalog.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Client {

    private Long id;

    @JsonProperty("telefone")
    private String phone;

    @JsonProperty("e-mail")
    private String email;

    @JsonProperty("nome")
    private String name;

}
