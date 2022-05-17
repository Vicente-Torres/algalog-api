package com.algaworks.algalog.model.embeddable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Embeddable
public class AddressEmbeddable {

    @NotBlank
    @Size(max = 100)
    @JsonProperty("rua")
    private String street;

    @JsonProperty("numero")
    @Pattern(regexp = "\\d*")
    private String number;

    @Size(max = 100)
    @JsonProperty("complemento")
    private String complement;

    @NotBlank
    @Size(max = 30)
    @JsonProperty("bairro")
    private String district;

}
