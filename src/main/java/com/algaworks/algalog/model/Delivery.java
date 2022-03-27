package com.algaworks.algalog.model;

import com.algaworks.algalog.model.eum.DeliveryStatus;
import com.algaworks.algalog.util.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Entity
public class Delivery extends BaseEntity<Long> {

    @Setter
    @Getter
    @ManyToOne
    @JsonProperty("cliente")
    private Client client;

    @ManyToOne
    @JsonProperty("destinatario")
    private Recipient recipient;

    @JsonProperty("taxa")
    private BigDecimal fee;

    @Setter
    @Enumerated(EnumType.STRING)
    @JsonProperty(value = "status", access = READ_ONLY)
    private DeliveryStatus status;

    @Setter
    @JsonProperty(value = "dataPedido", access = READ_ONLY)
    private LocalDateTime requestData;

    @Setter
    @JsonProperty(value = "dataFinalizacao", access = READ_ONLY)
    private LocalDateTime finishedData;

}
