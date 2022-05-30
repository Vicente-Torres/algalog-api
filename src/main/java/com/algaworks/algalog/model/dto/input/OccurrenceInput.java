package com.algaworks.algalog.model.dto.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class OccurrenceInput {

    @NotBlank
    @JsonProperty("descricao")
    private String description;
}
