package com.algaworks.algalog.model;

import com.algaworks.algalog.util.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Client extends BaseEntity<Long> {

    @JsonProperty("telefone")
    private String phone;

    @JsonProperty("e-mail")
    private String email;

    @JsonProperty("nome")
    private String name;

}
