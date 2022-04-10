package com.algaworks.algalog.model;

import com.algaworks.algalog.util.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Recipient extends BaseEntity<Long> {

    @NotBlank
    @Size(max = 60)
    @JsonProperty("nome")
    private String name;

    @Getter
    @Setter
    @OneToOne
    @JsonProperty("endereco")
    private Address address;

}