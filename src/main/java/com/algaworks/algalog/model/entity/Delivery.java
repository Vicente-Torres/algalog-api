package com.algaworks.algalog.model.entity;

import com.algaworks.algalog.model.embeddable.RecipientEmbeddable;
import com.algaworks.algalog.model.eum.DeliveryStatus;
import com.algaworks.algalog.util.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;
// TODO adicionar as mensagens de validação que estão faltando e remover as que não serão mais utilizadas
@Entity
public class Delivery extends BaseEntity<Long> {

    @Setter
    @Getter
    @ManyToOne
    @JsonProperty("cliente")
    private Client client;

    @Valid
    @Getter
    @Embedded
    @JsonProperty("destinatario")
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "recipient_name")),
            @AttributeOverride(name = "addressEmbeddable.street", column = @Column(name = "recipient_street")),
            @AttributeOverride(name = "addressEmbeddable.district", column = @Column(name = "recipient_district")),
            @AttributeOverride(name = "addressEmbeddable.number", column = @Column(name = "recipient_house_number")),
            @AttributeOverride(name = "addressEmbeddable.complement", column = @Column(name = "recipient_address_complement"))
    })
    private RecipientEmbeddable recipient;

    @NotNull(message = "bateu aq")
    @JsonProperty("taxa")
    private BigDecimal fee;

    @Setter
    @Enumerated(EnumType.STRING)
    @JsonProperty(value = "status", access = READ_ONLY)
    private DeliveryStatus status;

    @Setter
    @JsonProperty(value = "dataPedido", access = READ_ONLY)
    private LocalDateTime requestDate;

    @Setter
    @JsonProperty(value = "dataFinalizacao", access = READ_ONLY)
    private LocalDateTime finishedDate;

}
