package com.algaworks.algalog.model.eum;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DeliveryStatus {
    PENDING("Pendente"),
    FINISHED("Finalizada"),
    CANCELED("Cacelada");

    @JsonProperty("descricao")
    private String description;
}
