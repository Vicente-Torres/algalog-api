package com.algaworks.algalog.model.embeddable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Embeddable
public class RecipientEmbeddable {

    @Getter
    @NotBlank
    @Size(max = 60)
    @JsonProperty("nome")
    private String name;

    @Valid
    @Getter
    @Setter
    @JsonProperty("endereco")
    private AddressEmbeddable addressEmbeddable;

}