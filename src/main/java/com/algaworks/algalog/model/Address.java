package com.algaworks.algalog.model;

import com.algaworks.algalog.util.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Entity
public class Address extends BaseEntity<Long> {

    @NotBlank
    @Size(max = 100)
    @JsonProperty("rua")
    private String street;

    @JsonProperty("numero")
    private Integer number;

    @Size(max = 100)
    @JsonProperty("complemento")
    private String complement;

    @NotBlank
    @Size(max = 30)
    @JsonProperty("bairro")
    private String district;

}
