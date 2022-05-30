package com.algaworks.algalog.model.embeddable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Getter
@Embeddable
public class RecipientEmbeddable {

    @Setter
    @NotBlank
    @Size(max = 60)
    @JsonProperty("nome")
    private String name;

    @Valid
    @Setter
    @Embedded
    @JsonProperty("endereco")
    private AddressEmbeddable addressEmbeddable;

}